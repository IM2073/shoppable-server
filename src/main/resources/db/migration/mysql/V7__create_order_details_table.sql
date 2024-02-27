CREATE TABLE orderDetails (
    id INT NOT NULL AUTO_INCREMENT,
    product_id INT NOT NULL,
    order_id INT NOT NULL,
    amount INT NOT NULL,
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES products(id) ON DELETE CASCADE,
    FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE UNIQUE INDEX idx_product_id
ON orderDetails (product_id);

CREATE UNIQUE INDEX idx_order_id
ON orderDetails (order_id);