CREATE TABLE orders (
    id INT NOT NULL AUTO_INCREMENT,
    addressId INT NOT NULL,
    userId INT NOT NULL,
    totalPrice INT NOT NULL,
    status VARCHAR(10),
    dateIn TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    dateUp TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (addressId) REFERENCES addresses(id) ON DELETE CASCADE,
    FOREIGN KEY (userId) REFERENCES users(id) ON DELETE CASCADE ON UPDATE CASCADE
);
