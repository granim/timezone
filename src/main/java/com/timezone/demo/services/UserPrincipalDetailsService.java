package com.timezone.demo.services;

import com.timezone.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService {

    private UserRepository userRepository;

    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /*public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = this.userRepository.findByUserName(s);
        UserPrincipal userPrincipal = new UserPrincipal(user);
        return userPrincipal;
    }*/





}
