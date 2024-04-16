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
        System.out.println("ENTRE AL BUY");
        TicketShop ticketsShop = ticket.getItems();
        UserShop user = ticket.getPayer();

        TicketEntity ticketsEntities= ticketRepository.findById(ticketsShop.getId()).get();

        System.out.println("TICKEEETT" + ticketsEntities.getId());
        String sandboxInit = createPreference(ticketsEntities, user,findAccessToken(ticketsEntities.getId()));
        return sandboxInit;
    }

    private String  createPreference(TicketEntity ticket, UserShop userBuyer, String accessTokenVendedor) throws MPException, MPApiException {
        try {
            MercadoPagoConfig.setAccessToken(accessTokenVendedor);
            System.out.println("Access Token: " + accessToken);
            System.out.println("Access Token Vendedor: " + accessTokenVendedor);

            List<PreferenceItemRequest> items = new ArrayList<>();
            Double total = 0.0;

            items.add(createItemRequest(ticket));
//            for( TicketEntity t : tickets){
//                System.out.print(t.getPrice());
//                items.add(createItemRequest(t));
//                total += t.getPrice();
//            }
            System.out.println("Total: " + total);
            System.out.println("Items: " + items);
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

            System.out.println("Access Token Vendedor: " + accessTokenVendedor);

            MPRequestOptions requestOptions = MPRequestOptions.builder()
                    .customHeaders( customHeaders)
                    .build();

            Preference preference = client.create(preferenceRequest, requestOptions);

            System.out.println("Preference ID: " + preference.getInitPoint());

            preference.getItems().forEach(item -> {
                System.out.println("Item ID: " + item.getId());
                System.out.println("Item Title: " + item.getTitle());
                System.out.println("Item Description: " + item.getDescription());
                System.out.println("Item Quantity: " + item.getQuantity());
                System.out.println("Item Unit Price: " + item.getUnitPrice());
            });

            System.out.println( "status  " + preference.getInitPoint());
            System.out.println(preference.getResponse().getStatusCode());
            return preference.getSandboxInitPoint();
        }
        catch (MPApiException e){
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        catch (MPException e){
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
        catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            throw e;
        }
    }

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

    private String findAccessToken(Long ticketId) {
            TicketEntity ticket = ticketRepository.findById(ticketId).get();
            Long profileId = ticket.getOwner().getId();
            Optional<CredentialMPUser> credentialMPUser = mpUserRepository.findByProfileId(profileId);
            System.out.println("Accesstoken del profile obtenido del ticket id " + ticketId + credentialMPUser.get().getAccess_token());
            return credentialMPUser.get().getAccess_token();
    }

}
