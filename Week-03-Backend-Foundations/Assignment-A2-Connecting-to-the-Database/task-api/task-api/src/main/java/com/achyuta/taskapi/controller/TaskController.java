package com.achyuta.taskapi.controller;

import com.achyuta.taskapi.model.Task;
import com.achyuta.taskapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Task API", description = "CRUD Operations for Task Management")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Home API")
    @GetMapping("/")
    public Map<String, Object> home() {
        return Map.of(
                "name", "Task API",
                "version", "1.0",
                "endpoints", List.of("/tasks")
        );
    }

    @Operation(summary = "Health Check")
    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @Operation(summary = "Get all tasks")
    @GetMapping("/tasks")
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Get task by ID")
    @GetMapping("/tasks/{id}")
    public ResponseEntity<?> getTaskById(@PathVariable Integer id) {

        Task task = taskService.getTaskById(id);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Task " + id + " not found"));
        }

        return ResponseEntity.ok(task);
    }

    @Operation(summary = "Create a new task")
    @PostMapping("/tasks")
    public ResponseEntity<?> createTask(@RequestBody Task task) {

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Title is required"));
        }

        Task createdTask = taskService.addTask(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @Operation(summary = "Update an existing task")
    @PutMapping("/tasks/{id}")
    public ResponseEntity<?> updateTask(
            @PathVariable Integer id,
            @RequestBody Task task) {

        if (task.getTitle() == null || task.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest()
                    .body(Map.of("error", "Title is required"));
        }

        Task updatedTask = taskService.updateTask(id, task);

        if (updatedTask == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Task " + id + " not found"));
        }

        return ResponseEntity.ok(updatedTask);
    }

    @Operation(summary = "Delete a task")
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {

        boolean deleted = taskService.deleteTask(id);

        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Task " + id + "not found"));
        }

        return ResponseEntity.noContent().build();
    }
}