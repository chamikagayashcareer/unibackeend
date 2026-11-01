package com.uni.unibakeend.services;

import java.util.List;

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

    public List<RoleResponseDto> getRoles() {
        List<RoleResponse> roles = roleRepository.findAllList();

        return roles.stream().map(role -> new RoleResponseDto(role.getId(), role.getName())).toList();
    }
}
