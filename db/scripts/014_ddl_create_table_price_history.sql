CREATE TABLE IF NOT EXISTS PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before DECIMAL,
   after DECIMAL,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   post_id int REFERENCES auto_post(id) ON DELETE CASCADE
);



