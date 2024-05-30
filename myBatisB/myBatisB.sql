create database dao;

use dao;

create table ware
(
    id         int auto_increment primary key,
    name       varchar(100)   not null,
    brand      varchar(100),
    category   varchar(100),
    price      decimal(10, 2) not null,
    stock      int            not null,
    picAddress varchar(255)
);

insert into ware (id, name, brand, category, price, stock, picAddress)
values (1, '黄金手链', '周大福', 'bracelet', 1999.99, 100, 'static/image/bracelet/1.jpg'),
       (2, '钻石手链', '周生生', 'bracelet', 2999.99, 50, 'static/image/bracelet/2.jpg');

insert into ware (id, name, brand, category, price, stock, picAddress)
values (3, '珍珠项链', '老凤祥', 'necklace', 4999.99, 75, 'static/image/necklace/1.jpg'),
       (4, '翡翠项链', '六福珠宝', 'necklace', 5999.99, 60, 'static/image/necklace/2.jpg');

insert into ware (id, name, brand, category, price, stock, picAddress)
values (5, '白金戒指', '戴梦得', 'ring', 3999.99, 80, 'static/image/ring/1.jpg'),
       (6, '蓝宝石戒指', '谢瑞麟', 'ring', 4999.99, 70, 'static/image/ring/2.jpg');
