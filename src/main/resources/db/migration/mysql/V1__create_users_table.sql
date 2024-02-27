CREATE TABLE users (
    id INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(20) UNIQUE,
    name VARCHAR(20),
    role VARCHAR(20),
    password TEXT,
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id)
);

