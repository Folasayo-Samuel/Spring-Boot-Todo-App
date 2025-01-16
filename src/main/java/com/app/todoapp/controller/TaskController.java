package com.app.todoapp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.todoapp.services.TaskService;
import com.app.todoapp.models.Task;
import org.springframework.ui.Model;

@Controller
// @RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTask(Model model){
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping
    public String createTask(@RequestParam String title){
        taskService.createTask(title);
        return "redirect:/";
    }

    @GetMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);
        return "redirect:/";
    }

    @GetMapping("/{id}/toggle")
    public String toggleTask(@PathVariable Long id){
        taskService.toggleTask(id);
        return "redirect:/";
    }

    @PostMapping("/{id}/update")
    public String updateTask(@PathVariable Long id, @RequestParam String title){
        taskService.updateTask(id, title);
        return "redirect:/";
    }

    @GetMapping("/{id}/update")
    public String showUpdatePage(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);  
        model.addAttribute("task", task);  
        return "update_task";  
    }

}
