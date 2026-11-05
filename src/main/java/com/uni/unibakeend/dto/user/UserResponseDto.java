package com.uni.unibakeend.dto.user;

import java.util.List;

import com.uni.unibakeend.dto.role.RoleResponseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDto {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String departmentId;

    private String departmentName;

    private String batchId;

    private String batchName;

    List<RoleResponseDto> roles;
}
