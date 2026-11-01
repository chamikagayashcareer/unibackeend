package com.uni.unibakeend.dto.batch;

import java.math.BigInteger;
import java.util.List;

public record BatchDto(
        String id,
        String name,
        BigInteger intakeYear,
        String courseName,
        List<StudentDto> students) {
}
