package com.nixsolutions.service;

import com.nixsolutions.domain.User;
import com.nixsolutions.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
@EnableTransactionManagement
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void create(User user) {
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void update(User user) {
        userRepository.saveAndFlush(user);
    }

    @Transactional
    public void remove(User user) {
        userRepository.delete(user);
    }


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findById(Long id) {
        return userRepository.findByUserId(id);
    }
}


