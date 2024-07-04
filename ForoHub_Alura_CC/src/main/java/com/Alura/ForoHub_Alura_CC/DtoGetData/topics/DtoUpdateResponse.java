package com.Alura.ForoHub_Alura_CC.DtoGetData.topics;

public record DtoUpdateResponse(
        String message,
        int idTopic,
        int idAuthor,
        String solution
) {
}
