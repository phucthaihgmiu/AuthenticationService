-- -- USERS
INSERT INTO users(email, first_name, last_name, password)
VALUES
    ('phat@gmail.com', 'Phat', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('john@gmail.com', 'John', 'Smith', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('david@gmail.com', 'David', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('smith@gmail.com', 'Smith', 'Nguyen', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('adam@gmail.com', 'Adam', 'John', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq'),
    ('michael@gmail.com', 'Michael', 'Mike', '$2a$10$u5xPEnqTU3lcPPItCotEf./kp4k2zj4Q9Mnko5W4nBca4pvhLs7Zq');
--
-- -- ROLE
INSERT INTO roles(role)
VALUES
    ('ADMIN'),
    ('USER');
--
INSERT INTO users_roles(user_id, roles_id)
VALUES
    (1, 1),
    (1, 2),
    (2, 1),
    (3, 1),
    (4, 2),
    (5, 2),
    (5, 1),
    (6, 2);
