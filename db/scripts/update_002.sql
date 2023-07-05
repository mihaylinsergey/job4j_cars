CREATE TABLE IF NOT EXISTS BRAND(
   id SERIAL PRIMARY KEY,
   name VARCHAR(30)
);

ALTER TABLE car ADD COLUMN brand_id INT not null REFERENCES brand(id);

ALTER TABLE car DROP COLUMN brand;

CREATE TABLE IF NOT EXISTS CATEGORIES(
   id SERIAL PRIMARY KEY,
   name VARCHAR(30)
);

ALTER TABLE car ADD COLUMN category_id INT not null REFERENCES categories(id);

CREATE TABLE IF NOT EXISTS CAR_BODY(
   id SERIAL PRIMARY KEY,
   name VARCHAR(30)
);

ALTER TABLE car ADD COLUMN car_body_id INT not null REFERENCES car_body(id);

ALTER TABLE car ADD COLUMN auto_year INT not null;

ALTER TABLE car ADD COLUMN mileage INT not null;

ALTER TABLE engine ADD COLUMN volume DECIMAL(2,1) not null;

ALTER TABLE engine ADD COLUMN power_engine INT not null;

CREATE TABLE IF NOT EXISTS CAR_MODEL(
   id SERIAL PRIMARY KEY,
   name VARCHAR(30)
);

ALTER TABLE car ADD COLUMN car_model_id INT not null REFERENCES car_model(id);

CREATE TABLE IF NOT EXISTS CAR_COLOR(
   id SERIAL PRIMARY KEY,
   name VARCHAR(30)
);

ALTER TABLE car ADD COLUMN car_color_id INT not null REFERENCES car_color(id);

ALTER TABLE auto_post ADD COLUMN sold boolean;