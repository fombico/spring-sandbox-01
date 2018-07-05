package com.example.demo.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserObject, Long> {

    UserObject findUserObjectByUsername(String username);

    List<UserObject> findUserObjectsByRolesContains(String role);
}
