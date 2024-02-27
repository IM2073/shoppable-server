CREATE TABLE addresses (
    id INT NOT NULL AUTO_INCREMENT,
    city VARCHAR(20),
    state VARCHAR(20),
    postal_code VARCHAR(6),
    street_address VARCHAR(20),
    user_id INT NOT NULL,
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE UNIQUE INDEX idx_user_id
ON addresses (user_id);