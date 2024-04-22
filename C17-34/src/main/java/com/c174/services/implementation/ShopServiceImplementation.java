package com.c174.services.implementation;

import com.c174.exception.AlreadyExistsException;
import com.c174.models.mpuser.CredentialMPUser;
import com.c174.models.shop.ShopItem;
import com.c174.models.shop.UserShop;
import com.c174.models.ticket.TicketEntity;
import com.c174.models.ticket.TicketShop;
import com.c174.repositorys.MpUserRepository;
import com.c174.repositorys.ProfileRepository;
import com.c174.repositorys.TicketRepository;
import com.c174.services.abstraccion.ShopService;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.*;
import com.mercadopago.core.MPRequestOptions;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class ShopServiceImplementation implements ShopService {
    private final TicketRepository ticketRepository;
    private final ProfileRepository profileRepository;
    private final MpUserRepository mpUserRepository;
    @Value("${mercadopago.access_token}")
    private String accessToken;
    public ShopServiceImplementation(TicketRepository ticketRepository, ProfileRepository profileRepository, MpUserRepository mpUserRepository) {
        this.ticketRepository = ticketRepository;
        this.profileRepository = profileRepository;
        this.mpUserRepository = mpUserRepository;
    }
    @Override
    public String buyTickets(ShopItem ticket) throws AlreadyExistsException, MPException, MPApiException {
        TicketShop ticketsShop = ticket.getItems();
        UserShop user = ticket.getPayer();

        TicketEntity ticketsEntities= ticketRepository.findById(ticketsShop.getId()).get();

        String sandboxInit = createPreference(ticketsEntities, user,findAccessToken(ticketsEntities.getId()));
        return sandboxInit;
    }
    //Crea la preferencia de MercadoPago devolviendo el link hacia donde va a ser redirigido el comprador
    private String  createPreference(TicketEntity ticket, UserShop userBuyer, String accessTokenVendedor) throws MPException, MPApiException {
        try {
            MercadoPagoConfig.setAccessToken(accessTokenVendedor);

            List<PreferenceItemRequest> items = new ArrayList<>();

            items.add(createItemRequest(ticket));

            PreferenceBackUrlsRequest backUrls = PreferenceBackUrlsRequest.builder()
                    .success("https://microfrontmpreact-production.up.railway.app/ckeckout/success")
                    .pending("")
                    .failure("https://microfrontmpreact-production.up.railway.app/ckeckout/failure")
                    .build();

            PreferencePayerRequest payer = PreferencePayerRequest.builder()
                    .name(userBuyer.getName())
                    .email(userBuyer.getEmail())
                    .build();

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .backUrls(backUrls)
                    .autoReturn("approved")
                    .payer(payer)
                    .marketplaceFee(new BigDecimal(500))
                    .purpose("wallet_purchase")
                    .build();

            PreferenceClient client = new PreferenceClient();

            Map<String, String> customHeaders = new HashMap<>();
            customHeaders.put("Content-Type", "application/json");
            customHeaders.put("Authorization", "Bearer " + accessTokenVendedor);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders( customHeaders)
                    .build();

            Preference preference = client.create(preferenceRequest, requestOptions);

            return preference.getInitPoint();
        }
        catch (MPApiException e){
            throw e;
        }
        catch (MPException e){
            throw e;
        }
        catch (Exception e){
            throw e;
        }
    }
    // Crea un ItemRequest para la preferencia de MercadoPago
    private PreferenceItemRequest createItemRequest(TicketEntity ticket) {
        return PreferenceItemRequest.builder()
                .id(ticket.getId().toString())
                .title("Entrada para el evento: " + ticket.getEvent().getName())
                .description("Entrada para el evento: " + ticket.getEvent().getName())
                .quantity(1)
                .currencyId("ARS")
                .unitPrice(new BigDecimal("1000")) // TODO: cambiar esto
                .build();
    }

    // Busca el access token del vendedor atravez de la entidad profile hasta llegar al user
    private String findAccessToken(Long ticketId) {
            TicketEntity ticket = ticketRepository.findById(ticketId).get();
            Long userAppId = ticket.getOwner().getUser().getId();
            Optional<CredentialMPUser> credentialMPUser = mpUserRepository.findByUserApp_Id(userAppId);
            return credentialMPUser.get().getAccess_token();
    }

}
