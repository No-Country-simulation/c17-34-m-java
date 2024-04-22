package com.c174.controllers;

import com.c174.exception.AlreadyExistsException;
import com.c174.models.shop.ShopItem;
import com.c174.models.ticket.TicketShop;
import com.c174.services.abstraccion.ProfileService;
import com.c174.services.abstraccion.ShopService;
import com.c174.services.abstraccion.TicketService;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/shop")
@CrossOrigin("*") //TODO: cambiarlo por el dominio de la app
public class ShopController {
    private final ShopService shopService;
    public ShopController( ShopService shopService) {
        this.shopService = shopService;
    }

    @PostMapping("/create-preference")
    public ResponseEntity<?> buyTicket(@RequestBody ShopItem ticket) throws MPException, AlreadyExistsException, MPApiException {
        Map<String, Object> bodyResponse = new HashMap<>();
        try {
            String response = shopService.buyTickets(ticket);
            bodyResponse.put("data", response);
            bodyResponse.put("success", Boolean.TRUE);
            return ResponseEntity.ok(response);
        } catch ( MPException | MPApiException e) {
            bodyResponse.put("data", e.getMessage());
            bodyResponse.put("success", Boolean.FALSE);
            return ResponseEntity.badRequest().body(bodyResponse);
        }
    }

    @GetMapping("/accept-payment")
    public ResponseEntity<?> acceptPayment(@RequestParam("payment_id") String paymentId,
                                           @RequestParam("payment_status") String paymentStatus,
                                           @RequestParam("merchant_order_id") String merchantOrderId) {
        Map<String, Object> bodyResponse = new HashMap<>();
        bodyResponse.put("data", "Payment accepted");
        bodyResponse.put("success", Boolean.TRUE);
        return ResponseEntity.ok(bodyResponse);
    }

}
