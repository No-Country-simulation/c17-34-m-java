package com.c174.services.abstraccion;

import com.c174.exception.AlreadyExistsException;
import com.c174.models.shop.ShopItem;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;

public interface ShopService {
    String buyTickets(ShopItem tickets) throws AlreadyExistsException, MPException, MPApiException;

}
