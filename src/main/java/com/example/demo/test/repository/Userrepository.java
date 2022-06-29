package com.example.demo.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.test.model.User;

@Repository
public interface Userrepository extends JpaRepository<User, Long>{
 User findByEmail(String email);
}
