package com.Alura.ForoHub_Alura_CC.DtoGetData.topics;

import com.Alura.ForoHub_Alura_CC.models.Course;
import com.Alura.ForoHub_Alura_CC.models.Status;
import com.Alura.ForoHub_Alura_CC.models.User;

public record DtoCreateTopicToDatabase(
        String title,
        String message,
        User user,
        Course course,
        Status status
) {
}
