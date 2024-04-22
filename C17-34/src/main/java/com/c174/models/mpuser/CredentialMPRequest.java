package com.c174.models.mpuser;

import lombok.Data;

@Data
public class CredentialMPRequest {
    String access_token;
    String public_key;
    String refresh_token;
    Boolean live_mode;
    String user_id;
    String token_type;
    Long expires_in;
    String scope;
}
