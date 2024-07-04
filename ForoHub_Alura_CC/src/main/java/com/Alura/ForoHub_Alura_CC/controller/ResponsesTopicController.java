package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.Services.ServicesResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/responsetopic")
@SecurityRequirement(name = "bearer-key")
public class ResponsesTopicController {
    @Autowired
    ServicesResponses servicesResponses;

    @GetMapping("/{id}")
    @Operation(summary = "Obtiene la respuesta por topic", tags = "Get")
    public ResponseEntity getResponseByTopic(@PathVariable Integer id){
        return ResponseEntity.ok(servicesResponses.getResponseByTopic(id));
    }

}
