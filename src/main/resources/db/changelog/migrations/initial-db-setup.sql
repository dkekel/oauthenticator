create table account
(
  id       numeric primary key,
  username varchar(26)  not null unique,
  password varchar(255) not null,
  email    varchar(255) not null
);

create table oauth_client
(
  client_id     varchar(255) primary key,
  client_secret varchar(255)  not null,
  auto_approve  boolean default true,
  redirect_uri  varchar(1000) not null
);

create table scope
(
  id        numeric primary key,
  code      varchar(100) not null,
  client_id varchar(255) not null,
  CONSTRAINT scope_client_fk FOREIGN KEY (client_id)
    REFERENCES oauth_client (client_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE
);

create table grant_type
(
  id        numeric primary key,
  code      varchar(100) not null,
  client_id varchar(255) not null,
  CONSTRAINT grant_type_client_fk FOREIGN KEY (client_id)
    REFERENCES oauth_client (client_id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE
);

create table access_token
(
  token_id     varchar(255) primary key,
  user_id      numeric   not null,
  created_date timestamp not null,
  expire_in    numeric   not null,
  valid        boolean default 'Y',
  CONSTRAINT token_user_fk FOREIGN KEY (user_id)
    REFERENCES account (id) MATCH SIMPLE
    ON UPDATE NO ACTION ON DELETE CASCADE
);