package com.Alura.ForoHub_Alura_CC.DtoGetData.topics;

import com.Alura.ForoHub_Alura_CC.models.Course;
import com.Alura.ForoHub_Alura_CC.models.Status;
import com.Alura.ForoHub_Alura_CC.models.User;

import java.time.LocalDateTime;

public record DtoTopicData(
        String title,
        String message,
        LocalDateTime creationDate,
        Status status,
        User user,
        Course course
) {
}
