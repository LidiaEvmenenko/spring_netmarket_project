--liquibase formatted sql
--changeset lidij:create_table_carts
create table carts (
   id serial primary key,
   userId bigint,
   productId bigint,
   productTitle varchar(30) not null unique,
   productPrice numeric(8, 2),
   amount numeric(8, 2)
);


