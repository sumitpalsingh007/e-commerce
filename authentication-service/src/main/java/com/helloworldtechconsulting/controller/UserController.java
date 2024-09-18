package com.helloworldtechconsulting.controller;

import com.helloworldtechconsulting.model.ChangePasswordRequest;
import com.helloworldtechconsulting.model.entities.User;
import com.helloworldtechconsulting.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequest request, Principal connectedUser) {
        service.changePassword(request, connectedUser);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/details")
    public ResponseEntity<?> getDetails(Principal connectedUser) {
        User user = (User) service.loadUserByUsername(connectedUser.getName());// this is working fine but we have to return the only required user details

        Map<String, Object> response = new HashMap<>();
        response.put("id",user.getId());
        response.put("email", user.getEmail());
        response.put("firstname", user.getFirstname());
        response.put("lastname", user.getLastname());

        return ResponseEntity.ok(response);
    }
}