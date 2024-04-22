package com.c174.models.mpuser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;
import com.c174.models.mpuser.RequestToken;

public abstract class AbstractMPClient {

    @Value("${mercadopago.base_url_cred}")
    protected String baseUrl;
    protected RequestToken requestToken;
    protected final RestTemplate restTemplate;

    protected AbstractMPClient(RestTemplate restTemplate
                                ,RequestToken requestToken) {
        this.restTemplate = restTemplate;
        this.requestToken = requestToken;
    }

    protected HttpHeaders buildAuthToken(String token){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.set("Authorization", "Bearer " + token);
        return headers;
    }
}
