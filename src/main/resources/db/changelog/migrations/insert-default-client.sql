insert into oauth_client (client_id, client_secret, auto_approve, redirect_uri)
values ('gridwars',
        crypt('muchoseguridad', gen_salt('bf')), true, 'http://localhost:8081/user/login/client-app');

insert into grant_type (id, client_id, code)
values (2, 'gridwars', 'authorization_code');

insert into scope (id, client_id, code)
values (3, 'gridwars', 'player');
