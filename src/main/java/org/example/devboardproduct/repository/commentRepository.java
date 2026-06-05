package org.example.devboardproduct.repository;

import org.example.devboardproduct.entities.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface commentRepository extends JpaRepository<Comments, UUID> {
}
