package com.timezone.demo.services;

import com.timezone.demo.model.PdfUserDetails;
import com.timezone.demo.model.User;
import com.timezone.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found.");
        }


        return new PdfUserDetails(user);
    }
}
