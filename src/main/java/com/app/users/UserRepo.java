package com.app.users;

import com.app.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepo extends CrudRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
