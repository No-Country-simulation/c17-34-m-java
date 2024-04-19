package com.c174.models.mpuser;

import com.c174.models.user.UserResponse;
import lombok.Data;
import org.antlr.v4.runtime.Token;

import javax.swing.text.StyledEditorKit;

@Data
public class CredentialMPResponse {
    String access_token;
    String public_key;
    String refresh_token;
    Boolean live_mode;
    String user_id;
    String token_type;
    Long expires_in;
    String scope;
    UserResponse user;
}
