package com.achyuta.taskapi.service;

import com.achyuta.taskapi.model.Task;
import com.achyuta.taskapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public Task addTask(Task task) {
        return taskRepository.save(task);
    }

    // Update Task
    public Task updateTask(Integer id, Task updatedTask) {
        return taskRepository.update(id, updatedTask);
    }

    // Delete Task
    public boolean deleteTask(Integer id) {
        return taskRepository.deleteById(id);
    }
}
