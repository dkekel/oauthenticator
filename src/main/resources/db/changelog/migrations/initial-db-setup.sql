create table account
(
  id       serial primary key,
  username varchar(26)  not null unique,
  password varchar(255) not null,
  email    varchar(255) not null
);