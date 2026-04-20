package com.ecooil.repository;

import com.ecooil.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;   // 🔥 ADD THIS

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    List<User> findByRole(String role);  // 🔥 ADD THIS

}