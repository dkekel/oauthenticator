package ch.meyrin.lakeflower.oauthenticator.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.client.ClientDetailsUserDetailsService;
import org.springframework.util.StringUtils;

import java.util.Objects;

@EnableWebSecurity
@Order(1)
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final transient ClientDetailsService clientDetailsService;
    private final transient PasswordEncoder passwordEncoder;

    @Autowired
    public ApiSecurityConfiguration(final ClientDetailsService clientDetailsService,
                                    final PasswordEncoder passwordEncoder) {
        this.clientDetailsService = Objects.requireNonNull(clientDetailsService);
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public ClientDetailsUserDetailsService clientUserDetailsService() {
        ClientDetailsUserDetailsService userDetailsService = new ClientDetailsUserDetailsService(clientDetailsService);
        userDetailsService.setPasswordEncoder(passwordEncoder());
        return userDetailsService;
    }

    private PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return !StringUtils.hasText(encodedPassword) || passwordEncoder.matches(rawPassword, encodedPassword);
            }
            @Override
            public String encode(CharSequence rawPassword) {
                return passwordEncoder.encode(rawPassword);
            }
        };
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(clientUserDetailsService());
    }
}
