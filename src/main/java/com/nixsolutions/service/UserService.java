package com.nixsolutions.service;

import com.nixsolutions.domain.User;
import java.util.List;

public interface UserService {
    void create(User user);

    void update(User user);

    void remove(User user);

    List<User> findAll();

    User findByLogin(String login);

    User findByEmail(String email);

    User findById(Long id);
}
