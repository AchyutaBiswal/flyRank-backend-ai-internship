package com.flyrank.feedback;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class FeedbackApplicationTests {

    @Test
    void contextLoads() {
        // Verifies that the full Spring application context (security, JDBC, caching,
        // scheduling and all beans) wires up correctly against the H2 test database.
    }
}
