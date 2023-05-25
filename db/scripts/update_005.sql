CREATE TABLE PRICE_HISTORY(
   id SERIAL PRIMARY KEY,
   before BIGINT not null,
   after BIGINT not null,
   created TIMESTAMP WITHOUT TIME ZONE DEFAULT now(),
   price_history_id int REFERENCES price_history(id)
);

ALTER TABLE auto_post ADD COLUMN auto_user_id int REFERENCES auto_user(id);