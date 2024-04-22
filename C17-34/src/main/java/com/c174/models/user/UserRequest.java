package com.c174.models.user;

import com.c174.models.profile.ProfileRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest{
        private Long id;

        @Email(message = "El email debe ser valido.")
        private String email;
        @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres maximo.")
        private String password;
        private ProfileRequest profile;

}
