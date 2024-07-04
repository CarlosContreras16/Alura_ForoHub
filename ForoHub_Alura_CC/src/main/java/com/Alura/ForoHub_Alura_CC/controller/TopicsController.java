package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoCreateTopic;
import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoUpdateTopic;
import com.Alura.ForoHub_Alura_CC.DtoResponses.topics.DtoResponseGetDataTopic;
import com.Alura.ForoHub_Alura_CC.Services.ServiceTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics")
@SecurityRequirement(name = "bearer-key")
public class TopicsController {
    @Autowired
    ServiceTopic serviceTopic;

    @GetMapping
    @Operation(summary = "Obtiene todos los topics")
    public ResponseEntity getAllTopics(){
        return ResponseEntity.ok(serviceTopic);
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo topic")
    public ResponseEntity createTopic(@RequestBody @Valid DtoCreateTopic dtoCreateTopic, UriComponentsBuilder uriComponentsBuilder){
        DtoResponseGetDataTopic topicCreated = serviceTopic.createTopic(dtoCreateTopic);

        URI url = uriComponentsBuilder.path("/topics/{id}").buildAndExpand(topicCreated.id()).toUri();

        return ResponseEntity.created(url).body(topicCreated);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get the information of topic by code", tags = "Get")
    public ResponseEntity getTopicById(@PathVariable Long id){
        return ResponseEntity.ok(serviceTopic.getDataTopicById(id));
    }

    @PutMapping("/{id}")
    @Transactional
    @Operation(summary = "Update the information about a topic", tags = "Put")
    public ResponseEntity updateTopicById(@PathVariable Long id, @RequestBody DtoUpdateTopic dtoUpdateTopic){
        DtoResponseGetDataTopic dtoResponseGetDataTopic = serviceTopic.updateTopic(id, dtoUpdateTopic);

        return ResponseEntity.ok(dtoResponseGetDataTopic);
    }

    @DeleteMapping("/{id}")
    @Transactional
    @Operation(summary = "Elemina un topic by id", tags = "Delete")
    public ResponseEntity deleteTopicById(@PathVariable Long id){
        return ResponseEntity.ok(serviceTopic.DeleteTopic(id));
    }
}