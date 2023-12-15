CREATE TABLE Users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255),
    password VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    birthDate TIMESTAMP

);

CREATE TABLE Todolist (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE Item (
    id SERIAL PRIMARY KEY,
    content VARCHAR(1000),
    creation_date TIMESTAMP
    
);

-- Ajouter la clé étrangère pour représenter la relation un-à-un
ALTER TABLE Users
ADD COLUMN todo_list_id INTEGER REFERENCES TodoList(id);

-- Ajouter la clé étrangère pour représenter la relation un-à-un
ALTER TABLE Item
ADD COLUMN todo_list_id INTEGER REFERENCES TodoList(id);

INSERT INTO Users (email, password, first_name, last_name, birthDate)
VALUES ('utilisateur@example.com', 'motdepasse123', 'John', 'Doe', '2000-01-01T00:00:00');

INSERT INTO TodoList (name)
VALUES ('MaTodoList');

INSERT INTO Item (content, creation_date)
VALUES ('Contenue de l item', '2023-01-01T12:00:00');

-- Mettre à jour la clé étrangère dans Users
UPDATE Users SET todo_list_id = 1 WHERE id = 1

-- Mettre à jour la clé étrangère dans Users
UPDATE Item SET todo_list_id = 1 WHERE id = 1

DELETE FROM Users WHERE id = '3';
DELETE FROM TodoList WHERE id = '2';


select * from Users;
select * from TodoList;
select * from Item;
