--DROP TABLE IF EXISTS users;

CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(250) NOT NULL,
    eid VARCHAR(250) NOT NULL,
    name VARCHAR(250) NOT NULL
);

INSERT INTO users (email, eid, name) VALUES
('sam.bell@utexas.edu', 'snb2557', 'sam bell'),
('joshua.bell@utexas.edu', 'jcb2004', 'joshua bell'),
('theresa.paredes@utexas.edu', 'trp2012', 'theresa paredes');

CREATE TABLE IF NOT EXISTS tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(250) NOT NULL,
    user_id INT NOT NULL,
    deleted BOOLEAN DEFAULT FALSE
);

INSERT INTO tasks (description, user_id) VALUES
('Finish school', 1),
('Get a job...FAST!', 1),
('Get abs', 1),
('Become younger', 1),
('Take out the trash', 2),
('Mow the lawn', 2);

UPDATE tasks SET deleted = FALSE WHERE deleted IS NULL;
