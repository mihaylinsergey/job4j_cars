CREATE TABLE IF NOT EXISTS car (
    id SERIAL PRIMARY KEY,
    engine_id INT UNIQUE REFERENCES engine (id),
    owner_id INT REFERENCES owners(id),
    brand_id INT not null REFERENCES brand(id),
    category_id INT not null REFERENCES categories(id),
    car_body_id INT not null REFERENCES car_body(id),
    auto_year INT not null,
    mileage INT not null,
    car_model_id INT not null REFERENCES car_model(id),
    car_color_id INT not null REFERENCES car_color(id)
 );