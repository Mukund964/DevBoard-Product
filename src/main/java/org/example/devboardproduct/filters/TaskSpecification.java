package org.example.devboardproduct.filters;

import jakarta.persistence.criteria.Predicate;
import org.example.devboardproduct.dtos.TaskFilters;
import org.example.devboardproduct.entities.Tasks;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;


public class TaskSpecification {

    public static Specification<Tasks> withFilters(
            TaskFilters f) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (f.getProjectId() != null)
                predicates.add(cb.equal(
                        root.get("project").get("id"),
                        f.getProjectId()));

            if (f.getStatus() != null)
                predicates.add(cb.equal(
                        root.get("Status"), f.getStatus()));
            if (f.getPriority() != null)
                predicates.add(cb.equal(
                        root.get("Priority"), f.getPriority()));

            if (f.getAssigneeId() != null)
                predicates.add(cb.equal(
                        root.get("assignee").get("id"),
                        f.getAssigneeId()));

            return cb.and(predicates.toArray(
                    new Predicate[0]));
        };
    }
        }
