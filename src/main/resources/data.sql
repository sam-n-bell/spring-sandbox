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
    user_id INT NOT NULL
);

INSERT INTO tasks (description, user_id) VALUES
('a test description', 1);
