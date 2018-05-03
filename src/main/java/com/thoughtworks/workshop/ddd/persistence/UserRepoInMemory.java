package com.thoughtworks.workshop.ddd.persistence;

import com.thoughtworks.workshop.ddd.user.domain.model.User;
import com.thoughtworks.workshop.ddd.user.repo.UserRepo;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Repository
@Getter
public class UserRepoInMemory implements UserRepo {
    private Map<String, User> userRepo;

    public UserRepoInMemory() {
        this.userRepo = new HashMap<>();
    }

    @Override
    public String saveUser(User user) {
        userRepo.put(user.getId(), user);
        return user.getId();
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepo.values()
                .stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User findUserById(String id) {
        return userRepo.get(id);
    }
}
