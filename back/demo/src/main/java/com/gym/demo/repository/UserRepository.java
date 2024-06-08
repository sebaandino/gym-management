package com.gym.demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.gym.demo.models.UserEntity;
@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {

    UserEntity findUserEntityByDni(String dni);
    boolean existsByDni(String dni);
    void deleteById(String id);

}
