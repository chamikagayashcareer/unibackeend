package com.uni.unibakeend.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.uni.unibakeend.dto.role.RoleResponse;
import com.uni.unibakeend.dto.role.RoleResponseDto;
import com.uni.unibakeend.repositories.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Service
@AllArgsConstructor
@Getter
public class RoleService {
    private final RoleRepository roleRepository;
    private final Map<String, String> roleDisplay = new HashMap<>(
            Map.of(
                    "ROLE_ADMIN", "Administrator",
                    "ROLE_HOD", "Head of Department",
                    "ROLE_LECTURE", "Lecturer",
                    "ROLE_STUDENT", "Student"));

    public List<RoleResponseDto> getRoles() {
        List<RoleResponse> roles = roleRepository.findAllBy();

        return roles.stream().map(role -> {
            String displayName = roleDisplay.getOrDefault(role.getName(), role.getName());
            return new RoleResponseDto(role.getId(), displayName);
        }).toList();
    }
}
