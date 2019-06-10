package com.timezone.demo.services;

import com.timezone.demo.dto.UserRegistrationDto;
import com.timezone.demo.model.User;

public interface UserService extends CrudService<User, Long>{

        User findByUserName(String username);

       User findByEmail(String email);

       User save(UserRegistrationDto registration);

}
