CREATE TABLE IF NOT EXISTS auto_post (
    id SERIAL PRIMARY KEY,
    text TEXT,
    created DATE,
    auto_user_id INT REFERENCES auto_user (id),
    car_id INT unique REFERENCES car(id),
    post_history_id INT unique REFERENCES post_history(id),
    sold boolean
);