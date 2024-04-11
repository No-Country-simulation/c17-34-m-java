package com.c174.controllers;

import com.mercadopago.client.preference.PreferenceRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/mp")
public class MercadoPagoController{
    @Value("${mercadopago.client_id}")
    private String clientId;
    @Value("${mercadopago.client_secret}")
    private String clientSecret;

//    @PostMapping("/createPreference")
//    public void createPreference(@Valid @RequestBody PreferenceRequest preferenceRequest) {
//        MercadoPag
//        // Create preference
//    }
//
//    @PostMapping("/notification")
//    public void notification() {
//        // Notification
//    }

}
