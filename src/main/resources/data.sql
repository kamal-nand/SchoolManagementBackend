-- Insert default ADMIN user
INSERT INTO users (username, password, role)
VALUES (
    'admin',
    -- bcrypt hash of 'admin'
    '$2a$10$IQ.XYS5StRaY0W5jYBlDBu8DAmKXuegZivAX54xJiV6ShiJ.7lacK',
    'ADMIN'
);

-- Insert default TEACHER user
INSERT INTO users (username, password, role)
VALUES (
    'teacher',
    '$2a$10$LAlIvjez5Xn7B5c6kBPaQuGBS0zlmHpKeMDyBWsnXe0Y1MS6Y3lnG',
    'TEACHER'
);

-- Insert default STUDENT user
INSERT INTO users (username, password, role)
VALUES (
    'student',
    '$2a$10$ySBBTfWsxYuiMNop.pdyru/jFB82r4aHx3/EmLMw/ihq8ikWaNAUq',
    'STUDENT'
);
