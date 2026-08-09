package com.achyuta.taskapi.repository;

import com.achyuta.taskapi.model.Task;

import java.util.List;

public interface TaskRepository {

    List<Task> findAll();

    Task findById(Integer id);

    Task save(Task task);

    Task update(Integer id, Task task);

    boolean deleteById(Integer id);
}
