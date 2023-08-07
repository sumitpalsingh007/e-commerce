package com.helloworldtechconsulting.authenticationservice.repository;

import com.helloworldtechconsulting.authenticationservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// UserRepository.java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query
    User findByUsername(String username);
    @Query
    User findAllByUsernameAndPassword(String username, String password);
}
