package com.neonark.neonarkcapstone.repository;

import com.neonark.neonarkcapstone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}