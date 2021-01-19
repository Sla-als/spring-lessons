BEGIN;

DROP TABLE IF EXISTS products_new CASCADE;
CREATE TABLE products_new (id bigserial PRIMARY KEY, title VARCHAR(255), cost int );
INSERT INTO products_new (title, cost) VALUES
('product_1', 100),
('product_2', 200),
('product_3', 300),
('product_4', 400),
('product_5', 500),
('product_6', 600),
('product_7', 700);

DROP TABLE IF EXISTS buyers CASCADE;
CREATE TABLE buyers (id bigserial PRIMARY KEY, name VARCHAR(255));
INSERT INTO buyers(name) VALUES
('buyer_1'),
('buyer_2'),
('buyer_3'),
('buyer_4'),
('buyer_5');

DROP TABLE IF EXISTS buyer_cart CASCADE;
CREATE TABLE buyer_cart (product_id bigint, buyer_id bigint, FOREIGN KEY (product_id) REFERENCES products_new (id), FOREIGN KEY (buyer_id) REFERENCES buyers (id));
INSERT INTO buyer_cart (product_id, buyer_id) VALUES
(2, 5),
(3, 3),
(4, 1),
(5, 5),
(6, 1),
(1, 2),
(2, 2);


COMMIT;