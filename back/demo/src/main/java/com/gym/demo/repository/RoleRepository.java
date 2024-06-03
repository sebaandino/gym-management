package com.gym.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.demo.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    public Role findByRoleEnum(String roleName);

}
