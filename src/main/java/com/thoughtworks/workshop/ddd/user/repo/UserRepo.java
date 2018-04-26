package com.thoughtworks.workshop.ddd.user.repo;

import com.thoughtworks.workshop.ddd.user.domain.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo {
    Long saveUser(User user);

    User findUserByEmail(String email);
}
