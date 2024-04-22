package com.c174.services.abstraccion;

import com.c174.models.ticket.TicketEnterpriceDto;

import java.io.File;

public interface EnterpriseConsumeService {

    TicketEnterpriceDto checkTicket(File file);
    TicketEnterpriceDto lockTicket(Long id);
    TicketEnterpriceDto renewQr(File file);


}
