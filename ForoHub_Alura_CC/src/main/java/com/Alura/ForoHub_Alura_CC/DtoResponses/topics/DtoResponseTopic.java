package com.Alura.ForoHub_Alura_CC.DtoResponses.topics;

import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoUser;

import java.time.LocalDateTime;

public record DtoResponseTopic(
        LocalDateTime creationDate,
        String message,
        String solution,
        DtoUser author
) {
}
