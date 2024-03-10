ALTER TABLE orderDetails
DROP COLUMN amount;

ALTER TABLE orderDetails
ADD COLUMN quantity INT,
ADD COLUMN subtotal DECIMAL(10, 2);

