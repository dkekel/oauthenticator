package ch.meyrin.lakeflower.oauthenticator.configuration;

import ch.meyrin.lakeflower.oauthenticator.configuration.security.OAuthTokenEnhancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

@Configuration
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Value("${security.oauth2.client.client-id}")
    private transient String clientId;

    @Value("${security.oauth2.client.client-secret}")
    private transient String clientSecret;

    @Value("${security.oauth2.client.redirect-uri}")
    private transient String redirectUri;

    private final transient PasswordEncoder passwordEncoder;
    private final transient AuthenticationManager authenticationManager;

    @Autowired
    public AuthorizationServerConfiguration(final PasswordEncoder passwordEncoder,
                                            final AuthenticationManager authenticationManager) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new OAuthTokenEnhancer();
    }

    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //TODO configure request factory to get scopes from user roles
        //https://stackoverflow.com/questions/31345466/mapping-user-roles-to-oauth2-scopes-authorities
        //https://stackoverflow.com/questions/31391607/spring-oauth2-checkuserscopes-is-not-working-as-expected
        clients
                .inMemory()
                .withClient(clientId)
                .secret(passwordEncoder.encode(clientSecret))
                .scopes("read,write")
                .authorizedGrantTypes("authorization_code")
                .autoApprove(true)
                .redirectUris(redirectUri);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .tokenEnhancer(tokenEnhancer())
                .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
                .authenticationManager(authenticationManager);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }
}
