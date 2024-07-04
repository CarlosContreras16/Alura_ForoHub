package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.Services.ServiceTopic;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicsorder")
@SecurityRequirement(name = "bearer-key")
public class TopicsOrderController {
    @Autowired
    ServiceTopic serviceTopic;

    @GetMapping
    @Operation(summary = "Devuelve el ultimo record del topic", tags = "Get")
    public ResponseEntity getAllaTopics(@PageableDefault(size = 10)Pageable pagination){
        return  ResponseEntity.ok(serviceTopic.findLastTenRecords());
    }
}
