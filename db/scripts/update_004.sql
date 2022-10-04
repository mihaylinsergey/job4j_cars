CREATE TABLE IF NOT EXISTS auto_user (
    id SERIAL PRIMARY KEY,
    login TEXT,
    password TEXT
);

INSERT INTO auto_user (login, password) VALUES ('Ivanov', 'root');
INSERT INTO auto_user (login, password) VALUES ('Petrov', 'root');
INSERT INTO auto_user (login, password) VALUES ('Sidorov', 'root');