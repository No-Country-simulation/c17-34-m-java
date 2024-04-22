package com.c174.models.mpuser;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Data
@NoArgsConstructor
public class RequestToken {
    private String code;
    @Value("${mercadopago.client_secret}")
    private String client_secret;
    @Value("${mercadopago.client_id}")
    private String client_id;
    @Value("${mercadopago.test_token}")
    private String test_token;
    Enum<grantType> grant_type;
    @Value("${mercadopago.redirect_url}")
    private String redirect_uri;
    private UUID state;


    public RequestToken(String code, Enum<grantType> grant_type, UUID state) {
        this.code = code;
        this.grant_type = grant_type;
        this.test_token = Boolean.TRUE.toString();
        this.state = state;
    }
}
