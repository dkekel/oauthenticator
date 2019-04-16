create table account
(
  id       numeric primary key,
  username varchar(26)  not null unique,
  password varchar(255) not null,
  email    varchar(255) not null
);

CREATE TABLE oauth_client_details
(
  client_id               VARCHAR(256) PRIMARY KEY,
  resource_ids            VARCHAR(256),
  client_secret           VARCHAR(256),
  scope                   VARCHAR(256),
  authorized_grant_types  VARCHAR(256),
  web_server_redirect_uri VARCHAR(256),
  authorities             VARCHAR(256),
  access_token_validity   INTEGER,
  refresh_token_validity  INTEGER,
  additional_information  VARCHAR(4096),
  autoapprove             VARCHAR(256)
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