package com.c174.services.implementation;

import com.c174.exception.BadRequestException;
import com.c174.exception.EntityExistsException;
import com.c174.exception.EntityNotFoundException;
import com.c174.models.mpuser.*;
import com.c174.models.user.UserMapper;
import com.c174.repositorys.MpUserRepository;
import com.c174.repositorys.ProfileRepository;
import com.c174.services.abstraccion.MpUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.UUID;

@Service
public class MpUserServiceImpl extends AbstractMPClient implements MpUserService {
    private final MpUserRepository mpUserRepository;
    private final ProfileRepository profileRepository;
    private final CredentialMPmapper credentialMapper;
    private final UserMapper userMapper;

    public MpUserServiceImpl(MpUserRepository mpUserRepository,
                             ProfileRepository profileRepository,
                             CredentialMPmapper credentialMapper,
                             UserMapper userMapper,
                             RestTemplate restTemplate,
                             RequestToken requestToken) {
        super( restTemplate, requestToken);
        this.mpUserRepository = mpUserRepository;
        this.profileRepository = profileRepository;
        this.credentialMapper = credentialMapper;
        this.userMapper = userMapper;
    }

    public void createAuth(String code, UUID randomId){
        requestToken.setCode(code);
        requestToken.setGrant_type(grantType.authorization_code);
        requestToken.setState(randomId);

        ResponseEntity <CredentialMPRequest> response = restTemplate.postForEntity(baseUrl,requestToken, CredentialMPRequest.class);

        if(response.getStatusCode().is2xxSuccessful()){
            CredentialMPRequest credentialMPRequest = response.getBody();
            try {

                save(credentialMPRequest, randomId);
            } catch (EntityNotFoundException | EntityExistsException e) {
                throw new BadRequestException(e.getMessage());
            }
        }
    }

    private void save(CredentialMPRequest request, UUID randomId) throws EntityNotFoundException, EntityExistsException {
        Optional<CredentialMPUser> oCredentialMPUser =  mpUserRepository.findById(randomId);
        if(oCredentialMPUser.isEmpty()){
            throw new EntityExistsException("Credential already no exists");
        }
        CredentialMPUser credentialMPUser = oCredentialMPUser.get();
        credentialMPUser.setAccess_token(request.getAccess_token());
        credentialMPUser.setPublic_key(request.getPublic_key());
        credentialMPUser.setRefresh_token(request.getRefresh_token());
        credentialMPUser.setLive_mode(request.getLive_mode());
        credentialMPUser.setUser_id(request.getUser_id());
        credentialMPUser.setToken_type(request.getToken_type());
        credentialMPUser.setExpires_in(request.getExpires_in());
        credentialMPUser.setScope(request.getScope());

        mpUserRepository.save(credentialMPUser);
    }



}
