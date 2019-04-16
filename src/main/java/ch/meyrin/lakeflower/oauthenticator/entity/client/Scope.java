package ch.meyrin.lakeflower.oauthenticator.entity.client;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Scope implements Serializable {

    private static final long serialVersionUID = -1L;

    @Id
    @GeneratedValue
    private Long id;
    private String clientId;
    private String code;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
