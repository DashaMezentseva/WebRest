//package com.nixsolutions.service;
//
//import com.nixsolutions.domain.User;
//import com.nixsolutions.repository.UserRepository;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional(readOnly = true)
//@EnableTransactionManagement
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    public UserServiceImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
////    @Transactional
////    public void create(User user) {
////        this.userRepository.saveAndFlush(user);
////    }
////
////    @Transactional
////    public void update(User user) {
////        this.userRepository.saveAndFlush(user);
////    }
////
////    @Transactional
////    public void remove(User user) {
////        this.userRepository.delete(user);
////    }
////
////
////    public List<User> findAll() {
////        return this.userRepository.findAll();
////    }
////
////    public User findByLogin(String login) {
////        return this.userRepository.findByLogin(login);
////    }
////
////    public User findByEmail(String email) {
////        return this.userRepository.findByEmail(email);
////    }
////
////    public User findById(Long id) {
////        return this.userRepository.findByUserId(id);
////    }
//}
//
//
