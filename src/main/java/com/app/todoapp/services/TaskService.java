package com.app.todoapp.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.todoapp.repository.TaskRepository;

import jakarta.transaction.Transactional;

import com.app.todoapp.models.Task;

@Service
public class TaskService {

private final TaskRepository taskRepository;

public TaskService(TaskRepository taskRepository) {
this.taskRepository = taskRepository;
}
public List<Task> getAllTasks(){
    return taskRepository.findAll(); 
}

public void createTask(String title){
    Task task = new Task();
    task.setTitle(title);
    task.setCompleted(false);
    taskRepository.save(task);
}
@Transactional
public void updateTask(Long id, String title){
    Task task = taskRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Task not found"));
    task.setTitle(title);
    taskRepository.save(task);
}

public Task getTaskById(Long id) {
    return taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found for ID " + id));
}


public void toggleTask(Long id){
    Task task = taskRepository.findById(id)
    .orElseThrow(() -> new RuntimeException("Task not found"));
    task.setCompleted(!task.isCompleted());
    taskRepository.save(task);
}

public void deleteTask(Long id){
    taskRepository.deleteById(id);
}

}
