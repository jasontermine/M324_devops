package com.example.demo.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Task findByTaskDescription(String taskDescription);
}
