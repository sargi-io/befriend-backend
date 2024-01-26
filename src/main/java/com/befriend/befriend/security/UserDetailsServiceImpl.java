package com.befriend.befriend.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.befriend.befriend.model.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        com.befriend.befriend.model.User user = userRepository.findByName(name);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with name: " + name);
        }

        return User.withUsername(user.getEmail())
                .password(user.getPassword())
                .authorities("USER").build(); // You can customize the authorities based on your needs
    }
}