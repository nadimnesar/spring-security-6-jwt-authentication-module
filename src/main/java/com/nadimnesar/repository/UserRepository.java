package com.nadimnesar.repository;

import com.nadimnesar.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>  {
    User findByUsername(String username);
}