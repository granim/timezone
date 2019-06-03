package com.timezone.demo.repositories;

import com.timezone.demo.model.User;


public interface UserRepository extends CustomCrudRepository<User, Integer> {
    User getUserByUsername(String username);
}