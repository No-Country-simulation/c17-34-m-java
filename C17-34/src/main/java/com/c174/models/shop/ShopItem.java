package com.c174.models.shop;

import com.c174.models.ticket.TicketShop;
import lombok.Data;

import java.util.List;

@Data
public class ShopItem {
    private TicketShop items;
    private UserShop payer;
}
