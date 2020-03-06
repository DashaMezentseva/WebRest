package com.nixsolutions.repository;

import com.nixsolutions.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RoleRepository extends MongoRepository<Role, Long> {

//    @Query("select r from Role r where r.name = :name")
//    Role findByName(@Param("name") String name);
//
//    @Query("select r from Role r where r.roleId = :roleId")
//    Role findByRoleId(@Param("roleId") Long roleId);

}
