package com.c174.services.abstraccion;

import java.util.UUID;

public interface MpUserService {
    void createAuth(String code, UUID randomId);
}
