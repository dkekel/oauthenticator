package ch.meyrin.lakeflower.oauthenticator.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@FrameworkEndpoint
@RequestMapping("/api")
public class TokenController {

    private transient final ConsumerTokenServices tokenService;

    @Autowired
    public TokenController(@Qualifier("consumerTokenServices") final ConsumerTokenServices tokenService) {
        this.tokenService = tokenService;
    }

    @DeleteMapping("/logout/{token}")
    @ResponseStatus(HttpStatus.OK)
    public void revokeToken(final @PathVariable String token) {
        tokenService.revokeToken(token);
    }
}
