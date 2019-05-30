INSERT INTO account (username, password, email)
VALUES ('user',
        crypt('password', gen_salt('bf')),
        'd_kekel@gmail.com');