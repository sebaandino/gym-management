package com.gym.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gym.demo.models.Permissions;

@Repository
public interface PermissionsRepository extends JpaRepository<Permissions, Long> {

}
