package com.Alura.ForoHub_Alura_CC.Services.validation.topic;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateTopicToDatabase;
import com.Alura.ForoHub_Alura_CC.Services.validation.IValidationTopic;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.TopicRepository;
import com.Alura.ForoHub_Alura_CC.models.Topic;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class validationNoEqualTitleOrMessage implements IValidationTopic {

    @Autowired
    TopicRepository topicRepository;

    @Override
    public  void checkValidation(DtoCreateTopicToDatabase dataTopic)
    {
        Topic topicData = topicRepository.findTopicByTitleOrMessage(dataTopic.title(),dataTopic.message());

        if (topicData !=null){
            throw new ValidationException("The topic exists with title or message description");
        }
    }
}
