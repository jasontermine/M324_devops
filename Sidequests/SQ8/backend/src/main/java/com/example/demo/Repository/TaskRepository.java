package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;

import io.swagger.v3.oas.annotations.Hidden;

/**
 * Repository für die Task-Entität.
 */
@Hidden
@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    /**
     * Findet eine Aufgabe anhand ihrer Beschreibung.
     * @param taskDescription
     * @return Task
     */
    Task findByTaskDescription(String taskDescription);
}
