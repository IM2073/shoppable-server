CREATE TABLE carts (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    product_id INT NOT NULL, -- Added product_id column
    quantity INT NOT NULL,
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE UNIQUE INDEX idx_user_id
ON carts (user_id);

CREATE UNIQUE INDEX idx_product_id
ON carts (product_id);
