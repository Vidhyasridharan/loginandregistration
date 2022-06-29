package com.example.demo.test.services;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.demo.test.model.User;
import com.example.demo.test.web.dto.UserDto;



public interface Userservices extends UserDetailsService{
public User saveUser(UserDto userdto);


}
