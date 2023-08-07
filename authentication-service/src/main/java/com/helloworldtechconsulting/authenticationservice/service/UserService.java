package com.helloworldtechconsulting.authenticationservice.service;

import com.helloworldtechconsulting.authenticationservice.model.User;
import com.helloworldtechconsulting.authenticationservice.model.UserDTO;
import com.helloworldtechconsulting.authenticationservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

// UserService.java
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        // Set user roles and other details as needed
        userRepository.save(user);
    }

    public UserDetails loadUserByUsernameAndPassword(String username, String password) throws UsernameNotFoundException {
        User user = userRepository.findAllByUsernameAndPassword(username, passwordEncoder.encode(password));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
