package com.thoughtworks.workshop.ddd.user.repo;

import com.thoughtworks.workshop.ddd.user.domain.model.User;

public interface UserRepo {
    String saveUser(User user);

    User findUserByEmail(String email);

    User findUserById(String id);
}
