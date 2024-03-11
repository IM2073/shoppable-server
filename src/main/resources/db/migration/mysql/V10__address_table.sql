ALTER TABLE orders
DROP FOREIGN KEY orders_ibfk_1;

ALTER TABLE orders
DROP COLUMN address_id;

DROP TABLE IF EXISTS addresses;

