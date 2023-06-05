create table files
(
    id   serial primary key,
    name varchar,
    path varchar unique,
    post_id INT REFERENCES auto_post (id)
);

ALTER TABLE auto_post ADD COLUMN file_id INT REFERENCES files(id);

ALTER TABLE car ADD COLUMN brand varchar;