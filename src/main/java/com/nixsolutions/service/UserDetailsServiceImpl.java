//package com.nixsolutions.service;
//
//import com.nixsolutions.domain.User;
//import javax.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//@Transactional
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserDao userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        User user = userService.findByLogin(login);
//        org.springframework.security.core.userdetails.User.UserBuilder userBuilder = null;
//        if (user != null) {
//            org.springframework.security.core.userdetails.User.withUsername(login);
//            userBuilder.password(user.getPassword());
//            String authorities;
//            if (user.getRole().getRoleId().equals(2L)) {
//                authorities = "ADMIN";
//            } else {
//                authorities = "USER";
//            }
//            userBuilder.authorities(authorities);
//        } else {
//            userBuilder.authorities("GUEST");
//        }
//        return userBuilder.build();
//    }
//
//}
