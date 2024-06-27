create database dao;

use dao;

create table user
(
    uid      int auto_increment primary key,
    name     varchar(255) not null,
    password varchar(255) not null,
    role     varchar(255) not null,
    status   varchar(255) not null default '正常'
);

insert into user (name, password, role)
values ('张三', '123456', '管理员'),
       ('李四', '123456', '商家'),
       ('王五', '123456', '消费者');
