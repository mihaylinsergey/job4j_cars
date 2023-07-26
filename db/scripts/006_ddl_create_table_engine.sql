CREATE TABLE IF NOT EXISTS engine (
    id SERIAL PRIMARY KEY,
    name TEXT,
    volume DECIMAL(2,1) not null,
    power_engine INT not null
);
