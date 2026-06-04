package org.example.devboardproduct.repository;

import org.example.devboardproduct.entities.TaskPriority;
import org.example.devboardproduct.entities.TaskStatus;
import org.example.devboardproduct.entities.Tasks;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.SearchResult;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface TaskRepository extends JpaRepository<Tasks, UUID>, JpaSpecificationExecutor<Tasks> {
    @Query("SELECT t FROM Tasks t WHERE t.project.Id = :projectId " +
            "AND (:status IS NULL OR t.Status = :status) " +
            "AND (:priority IS NULL OR t.Priority = :priority)")
    List<Tasks> findTasksWithFilters(
            @Param("projectId") UUID projectId,
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority
    );

}
