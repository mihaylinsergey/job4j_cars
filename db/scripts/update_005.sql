CREATE TABLE IF NOT EXISTS PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   price_history_id int,
   FOREIGN KEY (price_history_id) REFERENCES auto_post(id)
);


