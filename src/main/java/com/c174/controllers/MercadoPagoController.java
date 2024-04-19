package com.c174.controllers;

import com.c174.services.abstraccion.MpUserService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/mp")
@CrossOrigin("*") //TODO: cambiarlo por el dominio de la app
public class MercadoPagoController{
    private MpUserService mpUserService;
    public MercadoPagoController(MpUserService mpUserService) {
        this.mpUserService = mpUserService;
    }
    @GetMapping("/oauth")
    public void createCredential(@RequestParam String code, @RequestParam UUID state) {
        mpUserService.createAuth(code, state);
    }


}
