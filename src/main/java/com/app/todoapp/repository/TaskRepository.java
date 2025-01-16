package com.app.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.app.todoapp.models.Task;

public interface TaskRepository extends JpaRepository<Task, Long>{

}
