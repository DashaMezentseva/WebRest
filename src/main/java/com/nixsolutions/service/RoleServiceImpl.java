//package com.nixsolutions.service;
//
//import com.nixsolutions.domain.Role;
//import com.nixsolutions.repository.RoleRepository;
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
//public class RoleServiceImpl implements RoleService {
//
//    private final RoleRepository roleRepository;
//
//    public RoleServiceImpl(RoleRepository roleRepository) {
//        this.roleRepository = roleRepository;
//    }
//
//    @Transactional
//    public void create(Role role) {
//        this.roleRepository.saveAndFlush(role);
//    }
//
//    @Transactional
//    public void update(Role role) {
//        this.roleRepository.saveAndFlush(role);
//    }
//
//    @Transactional
//    public void remove(Role role) {
//        this.roleRepository.delete(role);
//    }
//
//    public Role findByName(String name){
//        return this.roleRepository.findByName(name);
//    }
//
//    public List<Role> findAll() {
//        return this.roleRepository.findAll();
//    }
//
//    public Role findById(Long id){
//        return this.roleRepository.findByRoleId(id);
//    }
//
//}
