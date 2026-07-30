package com.achyuta.taskapi.service;

import com.achyuta.taskapi.model.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();

    public TaskService() {
        tasks.add(new Task(1, "Learn Spring Boot", false));
        tasks.add(new Task(2, "Complete FlyRank Assignment", false));
        tasks.add(new Task(3, "Practice REST APIs", true));
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public Task getTaskById(Integer id) {
        return tasks.stream()
                .filter(task -> task.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Task addTask(Task task) {

        int nextId = tasks.stream()
                .mapToInt(Task::getId)
                .max()
                .orElse(0) + 1;

        task.setId(nextId);
        task.setDone(false);

        tasks.add(task);

        return task;
    }

    // Update Task
    public Task updateTask(Integer id, Task updatedTask) {

        Task existingTask = getTaskById(id);

        if (existingTask == null) {
            return null;
        }

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDone(updatedTask.getDone());

        return existingTask;
    }

    // Delete Task
    public boolean deleteTask(Integer id) {

        Task task = getTaskById(id);

        if (task == null) {
            return false;
        }

        tasks.remove(task);

        return true;
    }
}