CREATE TABLE IF NOT EXISTS engine (
    id SERIAL PRIMARY KEY,
    name TEXT
);

CREATE TABLE IF NOT EXISTS post_history (
    id SERIAL PRIMARY KEY,
    startAt Timestamp,
    endAt Timestamp
);

CREATE TABLE IF NOT EXISTS auto_user (
    id SERIAL PRIMARY KEY,
    login varchar unique not null,
    password varchar not null,
    phoneNumber varchar not null
);

CREATE TABLE IF NOT EXISTS owners (
    id SERIAL PRIMARY KEY,
    name TEXT,
    user_id INT REFERENCES auto_user (id)
);

CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    brand varchar,
    engine_id INT UNIQUE REFERENCES engine (id),
    owner_id INT REFERENCES owners(id)
 );

 CREATE TABLE IF NOT EXISTS history_owners (
   id serial PRIMARY KEY,
   car_id int REFERENCES car(id),
   owner_id int REFERENCES owners(id),
   UNIQUE (car_id, owner_id)
);

CREATE TABLE IF NOT EXISTS auto_post (
    id SERIAL PRIMARY KEY,
    text TEXT,
    created DATE,
    auto_user_id INT REFERENCES auto_user (id),
    car_id INT unique REFERENCES car(id),
    post_history_id INT unique REFERENCES post_history(id)
);

create table IF NOT EXISTS files (
    id   serial primary key,
    name varchar,
    path varchar unique,
    file_post_id INT REFERENCES auto_post(id) ON DELETE CASCADE
    );

CREATE TABLE IF NOT EXISTS PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before DECIMAL,
   after DECIMAL,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   post_id int REFERENCES auto_post(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS participates (
   id serial PRIMARY KEY,
   user_id int REFERENCES auto_user(id),
   post_id int  REFERENCES auto_post(id),
   UNIQUE (user_id, post_id)
);


