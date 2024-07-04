package com.Alura.ForoHub_Alura_CC.Services.validation;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateTopicToDatabase;

public interface IValidationTopic {
    public  void checkValidation(DtoCreateTopicToDatabase dtoCreateTopicToDatabase);
}
