package com.repositories;

import com.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByFirstName(String firstName);

    User findByEmail(String email);

    User findAllById(Long userId);

    User findById(int userId);

}
