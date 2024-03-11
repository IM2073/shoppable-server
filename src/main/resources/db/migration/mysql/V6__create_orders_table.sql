CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    address_id INT NOT NULL,
    user_id INT NOT NULL,
    total_price INT NOT NULL,
    status VARCHAR(10),
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (address_id) REFERENCES addresses(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_user_id
ON orders (user_id);

CREATE INDEX idx_address_id
ON orders (address_id);