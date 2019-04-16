insert into oauth_client_details (client_id, client_secret, scope, authorized_grant_types,
                                  web_server_redirect_uri, access_token_validity, refresh_token_validity,
                                  additional_information, autoapprove)
values ('gridwars',
        crypt('muchoseguridad', gen_salt('bf')),
        'player',
        'authorization_code',
        'http://localhost:8081/user/login/client-app',
        864000,
        864000,
        '',
        'true');