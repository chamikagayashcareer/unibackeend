package com.uni.unibakeend.configurations;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.uni.unibakeend.entities.Role;
import com.uni.unibakeend.repositories.RoleRepository;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(RoleRepository roleRepository) {
        return args -> {
            if (roleRepository.findByName("ROLE_ADMIN").isEmpty()) {
                roleRepository.save(new Role("ROLE_ADMIN"));
            }
            if (roleRepository.findByName("ROLE_STUDENT").isEmpty()) {
                roleRepository.save(new Role("ROLE_STUDENT"));
            }
            if (roleRepository.findByName("ROLE_LECTURE").isEmpty()) {
                roleRepository.save(new Role("ROLE_LECTURE"));
            }
            if (roleRepository.findByName("ROLE_HOD").isEmpty()) {
                roleRepository.save(new Role("ROLE_HOD"));
            }
        };
    }

}
