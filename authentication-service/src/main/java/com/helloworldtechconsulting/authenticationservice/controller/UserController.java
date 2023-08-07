package com.helloworldtechconsulting.authenticationservice.controller;

import com.helloworldtechconsulting.authenticationservice.model.LoginRequest;
import com.helloworldtechconsulting.authenticationservice.model.UserDTO;
import com.helloworldtechconsulting.authenticationservice.service.UserService;
import com.helloworldtechconsulting.authenticationservice.util.JwtTokenUtil;
//import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    //@ApiOperation(value = "Register user")
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDTO userDTO) {
        userService.registerUser(userDTO);
        return ResponseEntity.ok("User registered successfully");
    }

    //@ApiOperation(value = "Authenticate user")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Your login logic
        var user = userService.loadUserByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
        String token = JwtTokenUtil.generateToken(null);

        // Return the token in the response
        return ResponseEntity.ok(token);
    }
}
