package com.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.models.User;

public interface UserRepo extends JpaRepository<User, Integer>{
    
}
