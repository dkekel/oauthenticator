package ch.meyrin.lakeflower.oauthenticator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableAuthorizationServer
@SpringBootApplication
public class OauthenticatorApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthenticatorApplication.class, args);
    }

}
