package com.nixsolutions.repository;

import com.nixsolutions.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends MongoRepository<User, Long> {

//    @Query("select u from User u where u.login = :login")
//    User findByLogin(@Param("login") String login);
//
//    @Query("select u from User u where u.email = :email")
//    User findByEmail(@Param("email") String email);
//
//    @Query("select u from User u where u.userId = :userId")
//    User findByUserId(@Param("userId") Long userId);
}
