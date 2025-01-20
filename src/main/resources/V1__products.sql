create database if not exists ciausdb;

create table if not exists products(
  id int primary key,
  name varchar(255)
);