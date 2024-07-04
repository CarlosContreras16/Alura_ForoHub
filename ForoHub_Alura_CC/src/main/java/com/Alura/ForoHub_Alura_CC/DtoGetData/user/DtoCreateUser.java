package com.Alura.ForoHub_Alura_CC.DtoGetData.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

public record DtoCreateUser(@NotBlank String username,
                            @Email String email,
                            @NotBlank String password,
                            @NotNull Integer typeOfProfile) {
}
