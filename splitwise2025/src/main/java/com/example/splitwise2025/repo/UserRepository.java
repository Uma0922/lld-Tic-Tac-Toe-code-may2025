package com.example.splitwise2025.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.splitwise2025.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long userId);
}