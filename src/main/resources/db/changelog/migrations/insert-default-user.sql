INSERT INTO account (id, username, password, email)
VALUES (1,
        'user',
        crypt('password', gen_salt('bf')),
        'd_kekel@gmail.com');