package com.uni.unibakeend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uni.unibakeend.entities.Profile;

public interface ProfileRepository extends JpaRepository<Profile, String> {

}
