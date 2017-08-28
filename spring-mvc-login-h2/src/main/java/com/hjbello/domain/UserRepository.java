package com.hjbello.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUserName(String username);
    
}