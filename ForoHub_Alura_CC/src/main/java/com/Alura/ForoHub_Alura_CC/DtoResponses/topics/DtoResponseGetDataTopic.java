package com.Alura.ForoHub_Alura_CC.DtoResponses.topics;

import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoUser;
import jdk.dynalink.linker.LinkerServices;

import java.time.LocalDateTime;
import java.util.List;

public record DtoResponseGetDataTopic(
        Integer id,
        String title,
        String message,
        LocalDateTime creationDate,
        String status,
        DtoUser user,
        String course,
        List<DtoResponseTopic> listResponses
) {
}
