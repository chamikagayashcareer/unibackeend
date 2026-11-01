package com.uni.unibakeend.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.unibakeend.dto.batch.AssignStudentRequest;
import com.uni.unibakeend.dto.batch.BatchDto;
import com.uni.unibakeend.dto.batch.CreateBatchDto;
import com.uni.unibakeend.dto.batch.StudentDto;
import com.uni.unibakeend.entities.Batch;
import com.uni.unibakeend.entities.Course;
import com.uni.unibakeend.entities.User;
import com.uni.unibakeend.errors.DuplicateResourceException;
import com.uni.unibakeend.errors.NotFoundResourceException;
import com.uni.unibakeend.repositories.BatchRepository;
import com.uni.unibakeend.repositories.CourseRepository;
import com.uni.unibakeend.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Service
@AllArgsConstructor
@Getter
@Setter
public class BatchServices {

    private final BatchRepository batchRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Transactional
    public Batch createBatch(CreateBatchDto dto) {
        List<Batch> batches = batchRepository.findAllByName(dto.getName());

        if (!batches.isEmpty()) {
            throw new DuplicateResourceException("Batch name already exist");
        }

        Batch newBatch = modelMapper.map(dto, Batch.class);

        Course course = courseRepository.findById(dto.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        newBatch.setCourse(course);

        return batchRepository.save(newBatch);
    }

    @Transactional(readOnly = true)
    public List<BatchDto> getBatches() {
        List<Batch> batches = batchRepository.findAllBatchStudentRows();

        return batches.stream().map(batch -> {
            List<StudentDto> students = batch.getStudents() == null
                    ? List.of()
                    : batch.getStudents().stream().map(student -> new StudentDto(
                            student.getId(),
                            student.getProfile().getFirstName(),
                            student.getProfile().getLastName())).toList();

            return new BatchDto(batch.getId(), batch.getName(), batch.getIntakeYear(),
                    batch.getCourse() == null ? null : batch.getCourse().getName(), students);
        }).toList();
    }

    @Transactional
    public void assignStudent(AssignStudentRequest request) {
        User student = userRepository.findStudentById(request.getStudentId())
                .orElseThrow(
                        () -> new NotFoundResourceException("Student not found for id: " + request.getStudentId()));

        if (student == null)
            throw new NotFoundResourceException("Student not found for id: " + request.getStudentId());

    }

    @Transactional
    public void deleteBatch(String batchId) {
        Batch batch = batchRepository.findById(batchId)
                .orElseThrow(() -> new NotFoundResourceException("Batch not found for id: " + batchId));

        batchRepository.delete(batch);
    }
}
