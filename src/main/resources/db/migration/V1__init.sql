create table products (
    id              bigserial primary key,
    title           varchar(255),
    price           int,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
    );
insert into products (title, price) values
('Bread', 10),
('Milk', 27),
('Cheese1', 94),
('Cheese2', 974),
('Cheese3', 964),
('Cheese4', 954),
('Cheese5', 94),
('Cheese6', 2924),
('Cheese7', 394);