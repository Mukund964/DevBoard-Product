package org.example.devboardproduct.repository;

import org.example.devboardproduct.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
     boolean existsByEmail(String email);
}
