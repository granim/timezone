
package com.timezone.demo.services.springdatajpa;

import com.timezone.demo.model.User;
import com.timezone.demo.repositories.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Profile("default")
public class UserSDJpaService {

    private final UserRepository userRepository;

    public UserSDJpaService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User findByUserName(String firstName) {
        return userRepository.findByFirstName(firstName);
    }


    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }


    public User findById(Long aLong) {
        return userRepository.findById(aLong).orElse(null);
    }


    public User save(User object) {
        return userRepository.save(object);
    }


    public void delete(User object) {
        userRepository.delete(object);
    }


    public void deleteById(Long aLong) {
        userRepository.deleteById(aLong);
    }
}

