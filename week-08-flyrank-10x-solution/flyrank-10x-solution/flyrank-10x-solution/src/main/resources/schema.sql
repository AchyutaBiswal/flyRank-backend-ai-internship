CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    email VARCHAR(160) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP NOT NULL
);

CREATE TABLE IF NOT EXISTS feedback (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    customer_name VARCHAR(120) NOT NULL,
    message VARCHAR(2000) NOT NULL,
    rating INT NOT NULL,
    category VARCHAR(60),
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_feedback_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS feedback_analysis (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    feedback_id BIGINT NOT NULL UNIQUE,
    sentiment VARCHAR(20) NOT NULL,
    summary VARCHAR(1000) NOT NULL,
    keywords VARCHAR(500),
    analyzed_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_analysis_feedback FOREIGN KEY (feedback_id) REFERENCES feedback(id)
);

CREATE TABLE IF NOT EXISTS reports (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    report_type VARCHAR(40) NOT NULL,
    file_path VARCHAR(500) NOT NULL,
    total_feedback INT NOT NULL,
    average_rating DOUBLE NOT NULL,
    positive_count INT NOT NULL,
    neutral_count INT NOT NULL,
    negative_count INT NOT NULL,
    generated_at TIMESTAMP NOT NULL
);
