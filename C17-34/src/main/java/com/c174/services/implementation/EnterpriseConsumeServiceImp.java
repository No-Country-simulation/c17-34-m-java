package com.c174.services.implementation;

import com.c174.models.ticket.TicketEnterpriceDto;
import com.c174.services.abstraccion.EnterpriseConsumeService;
import com.c174.utils.AbstractClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.File;

@Service
public class EnterpriseConsumeServiceImp extends AbstractClient implements EnterpriseConsumeService {

    protected EnterpriseConsumeServiceImp(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Override
    public TicketEnterpriceDto checkTicket(File file) {

        String url = baseURl + "/ticket/checkTicket";
        System.out.println(url);
        ResponseEntity<TicketEnterpriceDto> response = restTemplate.postForEntity(url, file, TicketEnterpriceDto.class);
        if (response.getStatusCode().is2xxSuccessful()) return response.getBody();
        else return null;
    }

    @Override
    public TicketEnterpriceDto lockTicket(Long id) {

        String url = baseURl + "/ticket/lock/{id}";

        Long pathVariable = id;

        ResponseEntity<TicketEnterpriceDto> response = restTemplate.getForEntity(url, TicketEnterpriceDto.class,pathVariable);

        if (response.getStatusCode().is2xxSuccessful()) return response.getBody();
        else return null;

    }

    @Override
    public TicketEnterpriceDto renewQr(File file) {

        String url = baseURl + "/ticket/renewQr";

        ResponseEntity<TicketEnterpriceDto> response = restTemplate.postForEntity(url,file, TicketEnterpriceDto.class);
        if (response.getStatusCode().is2xxSuccessful()) return response.getBody();
        else return null;
    }
}
