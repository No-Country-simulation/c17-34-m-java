package com.c174.models.mpuser;

import com.c174.models.user.UserMapper;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", uses={UserMapper.class})
public interface CredentialMPmapper {

    @Named("toCredentialMPResponse")
    @Mappings ({
            @Mapping(target = "scope", ignore = true)
    })
    CredentialMPResponse toCredentialMPResponse(CredentialMPUser credentialMPUser);

    @Named("toCredentialMPUser")
    @Mappings ({

    })
    CredentialMPUser toCredentialMPUser(CredentialMPRequest credentialMPRequest);

    @Named("toListCredentialMPResponse")
    @IterableMapping(qualifiedByName = "toCredentialMPResponse")
    List<CredentialMPResponse> toListCredentialMPResponse(List<CredentialMPUser> credentialMPUser);

}
