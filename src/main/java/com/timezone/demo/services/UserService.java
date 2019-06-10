package com.timezone.demo.services;

import com.timezone.demo.dto.UserRegistrationDto;
import com.timezone.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

       User findByUserName(String username);

       User findByEmail(String email);

       User save(UserRegistrationDto registration);






}
