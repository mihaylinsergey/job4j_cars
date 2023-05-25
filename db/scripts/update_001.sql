CREATE TABLE IF NOT EXISTS auto_user (
    id SERIAL PRIMARY KEY,
    login TEXT,
    password TEXT
);

CREATE TABLE IF NOT EXISTS AUTO_POST (
    id SERIAL PRIMARY KEY,
    text TEXT,
    created DATE,
    auto_user_id INT,
    FOREIGN KEY (auto_user_id) REFERENCES auto_user (id)
);

