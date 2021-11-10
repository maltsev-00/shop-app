create table product_photo
(
    id  UUID not null primary key,
    url varchar(255)
);

create table product
(
    id               UUID    not null primary key,
    description      varchar(255),
    name             varchar(255),
    price            integer not null,
    product_photo_id uuid references product_photo(id)
);
create table manufacturer_product
(
    id                      UUID not null primary key,
    name                    varchar(255),
    manufacturer_product_id UUID references product(id)
);

create table category_product
(
    id   UUID not null primary key,
    name varchar(255)
);


create table products_category
(
    product_id  UUID not null references product(id),
    category_id UUID not null references category_product(id)
);