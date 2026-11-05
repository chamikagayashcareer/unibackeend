package com.uni.unibakeend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.webmvc.autoconfigure.WebMvcProperties.Apiversion.Use;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uni.unibakeend.dto.role.RoleResponseDto;
import com.uni.unibakeend.dto.user.AddUserDto;
import com.uni.unibakeend.dto.user.UpdateUserDto;
import com.uni.unibakeend.dto.user.UserResponse;
import com.uni.unibakeend.dto.user.UserResponseDto;
import com.uni.unibakeend.entities.Batch;
import com.uni.unibakeend.entities.Department;
import com.uni.unibakeend.entities.Profile;
import com.uni.unibakeend.entities.Role;
import com.uni.unibakeend.entities.User;
import com.uni.unibakeend.errors.DuplicateResourceException;
import com.uni.unibakeend.errors.NotFoundResourceException;
import com.uni.unibakeend.repositories.BatchRepository;
import com.uni.unibakeend.repositories.DepartmentRepository;
import com.uni.unibakeend.repositories.ProfileRepository;
import com.uni.unibakeend.repositories.RoleRepository;
import com.uni.unibakeend.repositories.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BatchRepository batchRepository;
    private final DepartmentRepository departmentRepository;
    private final ProfileRepository profileRepository;

    private final ModelMapper modelMapper;

    private final Map<String, String> roleDisplay = new HashMap<>(
            Map.of(
                    "ROLE_ADMIN", "Administrator",
                    "ROLE_HOD", "Head of Department",
                    "ROLE_LECTURE", "Lecturer",
                    "ROLE_STUDENT", "Student"));

    @Transactional
    public String addUser(AddUserDto dto) {
        User user = userRepository.findByEmail(dto.getEmail());

        if (user != null)
            throw new DuplicateResourceException("Email already in use");

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new NotFoundResourceException("Role not found for given id"));

        User newUser = modelMapper.map(dto, User.class);

        Profile newProfile = new Profile();

        newProfile.setFirstName(dto.getFirstName());
        newProfile.setLastName(dto.getLastName());

        Profile profile = profileRepository.save(newProfile);

        newUser.setProfile(profile);

        newUser.addRole(role);

        if (dto.getBatchId() != null) {
            Batch batch = batchRepository.findById(dto.getBatchId())
                    .orElseThrow(() -> new NotFoundResourceException("Batch not found for given id"));

            newUser.setBatch(batch);
        }

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new NotFoundResourceException("Department not found for given id"));

            newUser.setDepartment(department);
        }

        User user2 = userRepository.save(newUser);

        // save hod in the department;
        if (role.getName().equals("ROLE_HOD") && dto.getDepartmentId() != null) {
            departmentRepository.updateHod(user2, dto.getDepartmentId());
        }

        return user2.getId();
    }

    @Transactional
    public String updateAddedUser(UpdateUserDto dto) {
        User user = userRepository.findById(dto.getId())
                .orElseThrow(() -> new NotFoundResourceException("User not found for id: " + dto.getId()));

        modelMapper.map(dto, user);

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new NotFoundResourceException("Role not found for given id"));

        user.cleanRole();

        user.addRole(role);

        Profile profile = profileRepository.findById(user.getProfile().getId())
                .orElseThrow(() -> new NotFoundResourceException("User Profile not found"));

        profile.setFirstName(dto.getFirstName());
        profile.setLastName(dto.getLastName());

        if (dto.getBatchId() != null) {
            Batch batch = batchRepository.findById(dto.getBatchId())
                    .orElseThrow(() -> new NotFoundResourceException("Batch not found for given id"));

            user.setBatch(batch);
        }

        if (dto.getDepartmentId() != null) {
            Department department = departmentRepository.findById(dto.getDepartmentId())
                    .orElseThrow(() -> new NotFoundResourceException("Department not found for given id"));

            user.setDepartment(department);
        }

        User user2 = userRepository.save(user);

        if (role.getName().equals("ROLE_HOD") && dto.getDepartmentId() != null) {
            departmentRepository.updateHod(user2, dto.getDepartmentId());
        }

        return user2.getId();
    }

    @Transactional(readOnly = true)
    public List<UserResponseDto> getUsers() {
        return userRepository.findAllUsers().stream().map((u) -> {
            UserResponseDto user = modelMapper.map(u, UserResponseDto.class);
            List<RoleResponseDto> roles = user.getRoles().stream().map(r -> {
                r.setName(roleDisplay.getOrDefault(r.getName(), r.getName()));
                return r;
            }).toList();
            user.setRoles(roles);
            return user;
        }).toList();
    }
}
