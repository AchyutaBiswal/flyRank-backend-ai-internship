-- Demo users
-- admin@flyrank.com / admin123  (role ADMIN)
-- john@flyrank.com  / password123 (role USER)
INSERT INTO users (id, name, email, password, role, created_at)
SELECT 1, 'FlyRank Admin', 'admin@flyrank.com', '$2a$10$YiEhduEcYaru9oA9Gs7jP.HAkrJ/M1/FxO8h21p1mTBqD8gZiMSJG', 'ADMIN', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 1);

INSERT INTO users (id, name, email, password, role, created_at)
SELECT 2, 'John Analyst', 'john@flyrank.com', '$2a$10$muaVcDUnEgK8whl.kb722ePwcGglCvK4I5585l4IqDsrvwqwhUNCC', 'USER', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM users WHERE id = 2);

-- Demo feedback entries submitted by John (user_id = 2)
INSERT INTO feedback (id, user_id, customer_name, message, rating, category, created_at)
SELECT 1, 2, 'Alice Kumar', 'The support team resolved my billing issue very quickly. Great experience!', 5, 'SUPPORT', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM feedback WHERE id = 1);

INSERT INTO feedback (id, user_id, customer_name, message, rating, category, created_at)
SELECT 2, 2, 'Ravi Shah', 'The app keeps crashing every time I try to upload a document. Very frustrating.', 1, 'BUG', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM feedback WHERE id = 2);

INSERT INTO feedback (id, user_id, customer_name, message, rating, category, created_at)
SELECT 3, 2, 'Priya Menon', 'Decent product overall, but the onboarding could be a bit clearer for new users.', 3, 'ONBOARDING', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM feedback WHERE id = 3);

INSERT INTO feedback (id, user_id, customer_name, message, rating, category, created_at)
SELECT 4, 2, 'Daniel Cho', 'Loving the new dashboard redesign, it is much faster and cleaner than before.', 5, 'UI', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM feedback WHERE id = 4);

INSERT INTO feedback (id, user_id, customer_name, message, rating, category, created_at)
SELECT 5, 2, 'Meera Iyer', 'Pricing feels a little high compared to competitors for the same feature set.', 2, 'PRICING', CURRENT_TIMESTAMP
WHERE NOT EXISTS (SELECT 1 FROM feedback WHERE id = 5);
