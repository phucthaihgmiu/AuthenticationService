package com.example.authenticationservice.repository;

import com.example.authenticationservice.entity.User;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
//public interface UserRepository extends CrudRepository<User, UUID> {
public interface UserRepository extends CassandraRepository<User, UUID>{
    @AllowFiltering
    Optional<User> findByEmail(String email);
}

