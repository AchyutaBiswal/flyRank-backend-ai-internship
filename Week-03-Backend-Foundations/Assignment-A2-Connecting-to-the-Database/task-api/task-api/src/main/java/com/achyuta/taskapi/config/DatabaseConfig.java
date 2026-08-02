package com.achyuta.taskapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Configuration
public class DatabaseConfig {

    @Bean
    CommandLineRunner initDatabase(DataSource dataSource) {

        return args -> {

            try (Connection connection = dataSource.getConnection();
                 Statement statement = connection.createStatement()) {

                // Create table if it doesn't exist
                statement.execute("""
                        CREATE TABLE IF NOT EXISTS tasks (
                            id INTEGER PRIMARY KEY AUTOINCREMENT,
                            title TEXT NOT NULL,
                            done BOOLEAN NOT NULL
                        );
                        """);

                // Check if table is empty
                var rs = statement.executeQuery("SELECT COUNT(*) FROM tasks");

                if (rs.next() && rs.getInt(1) == 0) {

                    statement.executeUpdate("""
                            INSERT INTO tasks(title, done)
                            VALUES
                            ('Learn Spring Boot', 0),
                            ('Complete FlyRank Assignment', 0),
                            ('Practice REST APIs', 1);
                            """);
                }

                System.out.println("✅ SQLite Database Ready");

            }

        };
    }
}