CREATE TABLE products (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    image_url TEXT NOT NULL,
    stock INT NOT NULL,
    price FLOAT NOT NULL,
    category_id INT NOT NULL,
    date_in TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    date_up TIMESTAMP NULL DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_category_id
ON products (category_id);
