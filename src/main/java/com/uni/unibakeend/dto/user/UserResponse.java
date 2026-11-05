package com.uni.unibakeend.dto.user;

import java.util.Set;

import com.uni.unibakeend.entities.Role;

public interface UserResponse {

    String getId();

    String getFirstName();

    String getLastName();

    String getEmail();

    String getDepartmentId();

    String getDepartmentName();

    String getBatchId();

    String getBatchName();

    Set<Role> getRoles();
}
