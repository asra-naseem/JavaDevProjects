package com.springboot.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.project.entities.User;

public interface UserRepo extends JpaRepository<User,Integer> {

}
