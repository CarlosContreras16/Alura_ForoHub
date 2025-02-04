package com.Alura.ForoHub_Alura_CC.DtoGetData.topics;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoUpdateTopic(
        @NotBlank String title,
        @NotBlank String message,
        @NotNull Integer user,
        @NotNull Integer course
) {
}
