CREATE TABLE IF NOT EXISTS participates (
   id serial PRIMARY KEY,
   user_id int REFERENCES auto_user(id),
   post_id int  REFERENCES auto_post(id),
   UNIQUE (user_id, post_id)
);




