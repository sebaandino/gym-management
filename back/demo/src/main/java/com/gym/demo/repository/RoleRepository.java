package com.gym.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gym.demo.models.Role;

@Repository
public interface RoleRepository extends MongoRepository<Role, String> {

    public Role findByRoleEnum(String roleName);

}
