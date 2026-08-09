package com.achyuta.taskapi.repository;

import com.achyuta.taskapi.model.Task;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PostgresTaskRepository implements TaskRepository {

    private static final RowMapper<Task> TASK_ROW_MAPPER = (rs, rowNum) -> new Task(
            rs.getInt("id"),
            rs.getString("title"),
            rs.getBoolean("done")
    );

    private final JdbcTemplate jdbcTemplate;

    public PostgresTaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Task> findAll() {
        return jdbcTemplate.query(
                "SELECT id, title, done FROM tasks ORDER BY id",
                TASK_ROW_MAPPER
        );
    }

    @Override
    public Task findById(Integer id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT id, title, done FROM tasks WHERE id = ?",
                    TASK_ROW_MAPPER,
                    id
            );
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public Task save(Task task) {
        Integer generatedId = jdbcTemplate.queryForObject(
                "INSERT INTO tasks (title, done) VALUES (?, ?) RETURNING id",
                Integer.class,
                task.getTitle(),
                false
        );

        task.setId(generatedId);
        task.setDone(false);

        return task;
    }

    @Override
    public Task update(Integer id, Task task) {
        int rowsUpdated = jdbcTemplate.update(
                "UPDATE tasks SET title = ?, done = ? WHERE id = ?",
                task.getTitle(),
                Boolean.TRUE.equals(task.getDone()),
                id
        );

        if (rowsUpdated == 0) {
            return null;
        }

        return findById(id);
    }

    @Override
    public boolean deleteById(Integer id) {
        int rowsDeleted = jdbcTemplate.update(
                "DELETE FROM tasks WHERE id = ?",
                id
        );

        return rowsDeleted > 0;
    }
}
