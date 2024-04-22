package com.c174.services.implementation;
import com.c174.exception.*;
import com.c174.models.embed.Audit;
import com.c174.models.mpuser.CredentialMPUser;
import com.c174.models.profile.ProfileEntity;
import com.c174.models.profile.ProfileMapper;
import com.c174.models.profile.ProfileRequest;
import com.c174.models.profile.ProfileResponse;
import com.c174.models.ticket.TicketEntity;
import com.c174.models.ticket.TicketMapper;
import com.c174.models.ticket.TicketRequest;
import com.c174.models.ticket.TicketResponse;
import com.c174.repositorys.ProfileRepository;
import com.c174.repositorys.TicketRepository;
import com.c174.services.abstraccion.ProfileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImplements implements ProfileService {
    @Value("${mercadopago.base_url_auth}")
    private String base_url_auth;
    private ProfileRepository profileRepository;
    private TicketRepository ticketRepository;
    private final ProfileMapper profileMapper;
    private final TicketMapper ticketMapper;
    public ProfileServiceImplements(ProfileRepository profileRepository, TicketRepository ticketRepository,  ProfileMapper profileMapper, TicketMapper ticketMapper) {
        this.profileRepository = profileRepository;
        this.ticketRepository = ticketRepository;
        this.profileMapper = profileMapper;
        this.ticketMapper = ticketMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProfileResponse> getAll() throws EntityNotFoundException {
        List<ProfileEntity> profiles = profileRepository.findAll();

        if(profiles == null || profiles.isEmpty()){
            throw new EntityNotFoundException("No profiles found");
        }

        List<ProfileResponse> profileResponse = profileMapper.toListProfileResponse(profiles);
        return profileResponse;
    }
    @Override
    @Transactional(readOnly = true)
    public ProfileResponse getProfileById(Long id) throws EntityNotFoundException {
        ProfileEntity profile = profileRepository.findById(id).orElse(null);

        if(profile == null){
            throw new EntityNotFoundException("No profiles found");
        }

        ProfileResponse profileResponse = profileMapper.toProfileResponse(profile);
        return profileResponse;
    }

    @Override
    @Transactional
    public ProfileResponse updateProfile(ProfileRequest profile) throws EntityNotFoundException, EntityUploadException {
        if(!profileRepository.existsById(profile.getId())){
            throw new EntityNotFoundException("Profile not found");
        }
        try {
            ProfileEntity profileEntity = profileRepository.updateProfile(profile.getLastname(), profile.getDocument(), profile.getName()).get();

            return profileMapper.toProfileResponse(profileEntity);
        }catch (Exception e){
            throw new EntityUploadException("Error updating profile");
        }
    }

    @Override
    @Transactional
    public String deleteProfile(Long id) throws EntityDeleteException, EntityNotFoundException {
        if(!profileRepository.existsById(id) ||
                profileRepository.findById(id).get().getIsPresent() == Boolean.FALSE){
            throw new EntityNotFoundException("Profile not found or already deleted");
        }
        try {
            profileRepository.findById(id).get().getAudit().preUpdate();
            profileRepository.updateIsPresent(id,Boolean.FALSE);
            return "Profile deleted";
        }catch (Exception e){
            throw new EntityDeleteException("Error deleting profile");
        }
    }
    @Override
    @Transactional
    public TicketResponse createTicket (Long profileId, TicketRequest newTicketRequest) throws EntityNotFoundException{
        if(!profileRepository.existsById(profileId) ||
                profileRepository.findById(profileId).get().getIsPresent() == Boolean.FALSE){
            throw new EntityNotFoundException("Profile not found or already deleted");
        }

        ProfileEntity profileEntity = profileRepository.findById(profileId).
                orElseThrow(() -> new EntityNotFoundException("Profile not found"));

        TicketEntity newTicketEntity = ticketMapper.toTicketEntity(newTicketRequest);
        newTicketEntity.setAudit(new Audit());
        TicketEntity savedTicket = ticketRepository.save(newTicketEntity);

        profileEntity.getTickets().add(savedTicket);
        savedTicket.setOwner(profileEntity);

        return ticketMapper.toTicketResponse(savedTicket);
    }

    @Override
    public ProfileResponse getProfileByUser(Long id) {
        Optional<ProfileEntity> profileEntity = profileRepository.findByUserIfIsAvailable(id);
        if (profileEntity.isPresent()) return profileMapper.toProfileResponse(profileEntity.get());
        return null;
    }


    public String authoMP(Long id) throws EntityNotFoundException {
        if(!profileRepository.existsById(id)){
            throw new EntityNotFoundException("Profile not found");
        }
        ProfileEntity profileEntity = profileRepository.findById(id).orElse(null);
        generateCredentialMP(profileEntity);
        return getUrlAuthMP(id);
    }
    //se crea un dato para la credencial de mp vinculado con el perfil, todo vacio menos random uuid y profile_id
    private  void generateCredentialMP( ProfileEntity profileEntity) {
        CredentialMPUser credentialMPUser = new CredentialMPUser();
        credentialMPUser.setId( UUID.randomUUID());

        profileRepository.findByUserId(profileEntity.getId());
        credentialMPUser.setUserApp(profileEntity.getUser());
        profileEntity.getUser().setCredentialMPUser(credentialMPUser);
        profileRepository.save(profileEntity);
    }
    //Crea el url para dar autorizacion a la app de mp
    public String getUrlAuthMP(Long id) throws EntityNotFoundException {
        if(!profileRepository.existsById(id) ||
                profileRepository.findById(id).get().getIsPresent() == Boolean.FALSE){
            throw new EntityNotFoundException("Profile not found or already deleted");
        }
        ProfileEntity profileEntity = profileRepository.findById(id).orElse(null);

        String uuidRandom = profileEntity.getUser().getCredentialMPUser().getId().toString();

        String url = base_url_auth+uuidRandom;
        return url;
    }


}
