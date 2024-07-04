package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoTopicSearchTitleAndYear;
import com.Alura.ForoHub_Alura_CC.Services.ServiceTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicssearch")
@SecurityRequirement(name = "bearear-key")
public class TopicsSearchController {
    @Autowired
    ServiceTopic serviceTopic;

    @GetMapping
    @Operation(summary = "Encuentra algunos topics por el titulo y el año", tags = "Get")
    public ResponseEntity getAllTopics(@RequestBody DtoTopicSearchTitleAndYear dtoTopicSearchTitleAndYear){
        return ResponseEntity.ok(serviceTopic.findTopicByTitleAndYear(dtoTopicSearchTitleAndYear));
    }
}
