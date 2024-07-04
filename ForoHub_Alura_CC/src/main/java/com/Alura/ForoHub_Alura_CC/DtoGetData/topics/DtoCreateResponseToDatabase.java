package com.Alura.ForoHub_Alura_CC.DtoGetData.topics;

import com.Alura.ForoHub_Alura_CC.models.Topic;
import com.Alura.ForoHub_Alura_CC.models.User;

public record DtoCreateResponseToDatabase(
        String message,
        Topic topic,
        User author,
        String solution
) {
}
