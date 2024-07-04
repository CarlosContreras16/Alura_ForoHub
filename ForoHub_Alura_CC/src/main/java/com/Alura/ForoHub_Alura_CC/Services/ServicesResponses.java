package com.Alura.ForoHub_Alura_CC.Services;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateResponse;
import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateResponseToDatabase;
import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoUpdateResponse;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseDeleteResponse;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseInfoResponse;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponsesInfoOfResponsesTopic;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.ResponsesRepository;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.TopicRepository;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.UserRepository;
import com.Alura.ForoHub_Alura_CC.models.Responses;
import com.Alura.ForoHub_Alura_CC.models.Topic;
import com.Alura.ForoHub_Alura_CC.models.User;
import jakarta.validation.ValidationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ServicesResponses {
    @Autowired
    ResponsesRepository responsesRepository;

    @Autowired
    TopicRepository topicRepository;

    @Autowired
    UserRepository userRepository;

    public DtoResponseInfoResponse getResponseById(int responseId){
        Optional<Responses> responseGetter = responsesRepository.findById(Long.valueOf(responseId));

        if (responseGetter.isEmpty()){
            throw new ValidationException("La respuesta es incorrecta");
        }
        Responses responses = responseGetter.get();

        DtoResponseInfoResponse dtoResponseInfoResponse = new DtoResponseInfoResponse(
                responses.getCode(),
                responses.getTopic().getCode(),
                responses.getMessage(),
                responses.getSolution(),
                responses.getCreationdate(),
                responses.getAuthor().getUsername()
                );
        return dtoResponseInfoResponse;
    }

    public DtoResponsesInfoOfResponsesTopic createResponse(DtoCreateResponse dtoCreateResponse){
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoCreateResponse.idAuthor()));
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(dtoCreateResponse.idTopic()));

        if (userGetter.isEmpty()){
            throw new ValidationException("El codigo del usuario no existe");
        }
        if (topicGetter.isEmpty()){
            throw new ValidationException("El topic no existe");
        }

        DtoCreateResponseToDatabase dtoCreateResponseToDatabase = new DtoCreateResponseToDatabase(
                dtoCreateResponse.message(),
                topicGetter.get(),
                userGetter.get(),
                dtoCreateResponse.solution()
        );

        Responses response = new Responses(dtoCreateResponseToDatabase);
        responsesRepository.save(response);

        return fillData(topicGetter.get(), response);
    }

    public DtoResponsesInfoOfResponsesTopic updateResponse(Long idResponse, DtoUpdateResponse dtoUpdateResponse){
        //encuentra los objetos con el ids
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoUpdateResponse.idAuthor()));
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(dtoUpdateResponse.idTopic()));

        if (userGetter.isEmpty())
        {
            throw new ValidationException("El codigo del usuario no existe");
        }
        if (topicGetter.isEmpty()){
            throw new ValidationException("El topic no existe");
        }

        Responses response = responsesRepository.findById(idResponse).get();

        response.setMessage(dtoUpdateResponse.message());
        response.setSolution(dtoUpdateResponse.solution());
        response.setAuthor(userGetter.get());
        response.setTopic(topicGetter.get());

        return  fillData(topicGetter.get(), response);
    }
    public DtoResponseDeleteResponse deleteResponse(Long id){
        try {
            DtoResponseDeleteResponse dtoResponseDeleteResponse = new DtoResponseDeleteResponse(200,
                    "La respuesta fue borrada exitosamente");

            responsesRepository.deleteById(id);
            return  dtoResponseDeleteResponse;
        }catch (Exception e){
            throw new ValidationException("Ocurrió un error al eliminar la respuesta");
        }
    }

     public List<DtoResponsesInfoOfResponsesTopic> getResponseByTopic(int topicId){
        Optional<Topic> topicGetter = topicRepository.findById(Long.valueOf(topicId));
        List<DtoResponsesInfoOfResponsesTopic> listResponses = new ArrayList<>();

        if (topicGetter.isEmpty()){
            throw new ValidationException("El codigo del usuario no existe");
        }

        Topic topic = topicGetter.get();

        List<Responses> responsesList = responsesRepository.findByTopicId(topicId);

        for(Responses r: responsesList){
            DtoResponsesInfoOfResponsesTopic dtoResponsesInfoOfResponsesTopic = fillData(topic,r);

            listResponses.add(dtoResponsesInfoOfResponsesTopic);
        }
        return listResponses;
     }

     public DtoResponsesInfoOfResponsesTopic fillData(Topic topic, Responses response){
        DtoResponsesInfoOfResponsesTopic dtoResponsesInfoOfResponsesTopic = new DtoResponsesInfoOfResponsesTopic(
                topic.getCode(),
                topic.getMessage(),
                response.getCode(),
                response.getMessage(),
                response.getSolution(),
                response.getCreationdate(),
                response.getAuthor().getUsername()
        );

        return dtoResponsesInfoOfResponsesTopic;
     }
}
