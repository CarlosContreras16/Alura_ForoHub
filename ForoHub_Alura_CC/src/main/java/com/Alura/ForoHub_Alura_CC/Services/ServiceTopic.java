package com.Alura.ForoHub_Alura_CC.Services;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.*;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseDeleteTopic;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseGetDataTopic;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseTopic;
import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoUser;
import com.Alura.ForoHub_Alura_CC.Services.validation.IValidationTopic;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.*;
import com.Alura.ForoHub_Alura_CC.models.*;
import jakarta.validation.ValidationException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ServiceTopic {
    @Autowired
    TopicRepository topicRepository;

    @Autowired
    ResponsesRepository responsesRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StatusRepository statusRepository;

    @Autowired
    List<IValidationTopic> validationTopics;


   public List<DtoResponseGetDataTopic> getAllDataTopic(){
        List<Topic> listTopic = topicRepository.findAll();
        List<DtoResponseGetDataTopic> topics = new ArrayList<>();

        for (Topic t : listTopic)
        {
            DtoResponseGetDataTopic dataTopic = fillDtoGetDataTopic(t);
            topics.add(dataTopic);
        }
        return topics;
    }
    public DtoResponseGetDataTopic createTopic(DtoCreateTopic dtoCreateTopic){
       //Encuentra los obejtos con el id
        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoCreateTopic.user()));
        Optional<Course> courseGetter = courseRepository.findById(Long.valueOf(dtoCreateTopic.course()));
        Optional<Status> statusGetter = statusRepository.findById(Long.valueOf(1));

        if (userGetter.isEmpty()){
            throw new ValidationException("El usuario no existe");
        }

        if (courseGetter.isEmpty()){
            throw new ValidationException("El curso no existe");
        }

        if (statusGetter.isEmpty()){
            throw new ValidationException("El estado no existe");
        }
        DtoCreateTopicToDatabase dtoCreateTopicToDatabase = new DtoCreateTopicToDatabase(
                dtoCreateTopic.title(),
                dtoCreateTopic.message(),
                userGetter.get(),
                courseGetter.get(),
                statusGetter.get()
        );

        validationTopics.forEach(t -> t.checkValidation(dtoCreateTopicToDatabase));
        Topic topicData = new Topic(dtoCreateTopicToDatabase);

        topicRepository.save(topicData);

        DtoResponseGetDataTopic dataTopic = fillDtoGetDataTopic(topicData);

        return dataTopic;
    }

    public DtoResponseGetDataTopic getDataTopicById(Long id){
       Optional<Topic> optionalTopic = topicRepository.findById(id);

       if (optionalTopic.isEmpty()){
           throw new ValidationException("El topic no está presente");
       }

       Topic topicDataa = optionalTopic.get();

       DtoResponseGetDataTopic dataTopic = fillDtoGetDataTopic(topicDataa);
       return dataTopic;
    }
    public DtoResponseGetDataTopic updateTopic (Long id, DtoUpdateTopic dtoUpdateTopic){
        Optional<Topic> topicGetter = topicRepository.findById(id);

        if (topicGetter.isEmpty()) {
            throw new ValidationException("El Topic no existe");
        }

        Topic topic = topicGetter.get();

        Optional<User> userGetter = userRepository.findById(Long.valueOf(dtoUpdateTopic.user()));
        Optional<Course> courseGetter = courseRepository.findById(Long.valueOf(dtoUpdateTopic.course()));
        Optional<Status> statusGetter = statusRepository.findById(Long.valueOf(1));

        if (userGetter.isEmpty()) {
            throw new ValidationException("El usuario no existe");
        }
        if (statusGetter.isEmpty()) {
            throw new ValidationException("El estado no existe");
        }

        topic.setMessage(dtoUpdateTopic.message());
        topic.setTitle(dtoUpdateTopic.title());
        topic.setAuthor(userGetter.get());
        topic.setCourse(courseGetter.get());
        topic.setStatus(statusGetter.get());

        DtoResponseGetDataTopic getDataTopic = fillDtoGetDataTopic(topic);

        return getDataTopic;
    }

    public DtoResponseDeleteTopic DeleteTopic(Long id){
       DtoResponseDeleteTopic dtoResponseDeleteTopic = new DtoResponseDeleteTopic(200,
               "El record ha sido eliminado");

       topicRepository.deleteById(id);

       return dtoResponseDeleteTopic;
    }

    public List<DtoResponseGetDataTopic> findLastTenRecords(){
       List<Topic> listTopics = topicRepository.findLastTenRecordsByCreationDate();
       List<DtoResponseGetDataTopic> topics = new ArrayList<>();

       for (Topic t : listTopics){
           DtoResponseGetDataTopic dataTopic = fillDtoGetDataTopic(t);

           topics.add(dataTopic);
       }
       return topics;
    }

    public List<DtoResponseGetDataTopic> findTopicByTitleAndYear(DtoTopicSearchTitleAndYear dtoTopicSearchTitleAndYear){
       List<Topic> listTopics = topicRepository.findTopicByCourseNameAndYear(dtoTopicSearchTitleAndYear.courseName(),
               dtoTopicSearchTitleAndYear.year());
       List<DtoResponseGetDataTopic> topics = new ArrayList<>();

       for (Topic t : listTopics){
           DtoResponseGetDataTopic dataTopic = fillDtoGetDataTopic(t);

           topics.add(dataTopic);
       }

       return topics;
    }

    public  DtoResponseGetDataTopic fillDtoGetDataTopic(Topic topicData){
        List<Responses> listResponses = responsesRepository.findBbyTopic(topicData);

        //Obtiene la lista de las respuestas
        List<DtoResponseTopic> listDtoResponseTopic = listResponses.stream()
                .map(r -> new DtoResponseTopic(r.getCreationdate(),
                        r.getMessage(),
                        r.getSolution(),
                        new DtoUser(r.getAuthor().getUsername(), r.getAuthor().getEmail())))
                .toList();
        DtoResponseGetDataTopic dtoResponseGetDataTopic = new DtoResponseGetDataTopic(
                topicData.getCode(),
                topicData.getTitle(),
                topicData.getMessage(),
                topicData.getCreationDate(),
                topicData.getStatus().getDescription(),
                new DtoUser(topicData.getAuthor().getUsername(), topicData.getAuthor().getEmail()),
                topicData.getCourse().getName(),
                listDtoResponseTopic
        );
        return dtoResponseGetDataTopic;
    }
}
