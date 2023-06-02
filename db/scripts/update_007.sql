CREATE TABLE IF NOT EXISTS engine (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    name TEXT,
    engine_id INT,
    FOREIGN KEY (engine_id) UNIQUE REFERENCES engine (id)
);

CREATE TABLE IF NOT EXISTS owners (
    id SERIAL PRIMARY KEY,
    name TEXT,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES auto_user (id)
);

CREATE TABLE IF NOT EXISTS history_owners (
   id serial PRIMARY KEY,
   car_id int not null REFERENCES car(id),
   owner_id int not null REFERENCES owners(id),
   UNIQUE (car_id, owner_id)
);

CREATE TABLE IF NOT EXISTS history (
    id SERIAL PRIMARY KEY,
    startAt Timestamp,
    endAt Timestamp
);

ALTER TABLE auto_post ADD COLUMN car_id INT REFERENCES car(id);