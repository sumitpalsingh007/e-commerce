package com.helloworldtechconsulting.repository;

import java.util.List;
import java.util.Optional;

import com.helloworldtechconsulting.model.entities.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TokenRepository extends JpaRepository<Token, Integer> {

    @Query(value = """
      select t from com.helloworldtechconsulting.model.entities.Token t \s
      inner join com.helloworldtechconsulting.model.entities.User u \s
      on t.user.id = u.id \s
      where u.id = :id and (t.expired = false or t.revoked = false) \s
      """)
    List<Token> findAllValidTokenByUser(Integer id);

    Optional<Token> findByToken(String token);
}