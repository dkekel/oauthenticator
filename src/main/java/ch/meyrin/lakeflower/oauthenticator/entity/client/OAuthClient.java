package ch.meyrin.lakeflower.oauthenticator.entity.client;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.Collection;

@Entity(name = "OAUTH_CLIENT")
public class OAuthClient implements Serializable {
    private static final long serialVersionUID = -1L;

    @Id
    private String clientId;
    private String clientSecret;
    private boolean autoApprove;
    private String redirectUri;

    @OneToMany(mappedBy = "clientId")
    private Collection<Scope> scopes;
    @OneToMany(mappedBy = "clientId")
    private Collection<GrantType> grantTypes;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public boolean isAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(boolean autoApprove) {
        this.autoApprove = autoApprove;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
