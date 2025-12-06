package com.ridehub.backend.repository;

import com.ridehub.backend.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    Optional<Account> findByUsername(String username);
    boolean existsByUsername(String username);
}
