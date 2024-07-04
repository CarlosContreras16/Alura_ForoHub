package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoGetData.topics.DtoUpdateResponse;
import com.Alura.ForoHub_Alura_CC.Services.ServicesResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/responses")
@SecurityRequirement(name="bearer-key")
public class ResponsesController {
    @Autowired
    ServicesResponses servicesResponses;

    @GetMapping("/{id}")
    @Operation(summary = "Get the information")
    public ResponseEntity getResponsesByTopic(@PathVariable Integer id){
        return ResponseEntity.ok(servicesResponses.getResponseById(id));
    }

    @PostMapping("/{id}")
    @Operation(summary = "Actualiza la información")
    @Transactional
    public ResponseEntity updateResponsesData(@PathVariable Integer id, @RequestBody DtoUpdateResponse dtoUpdateResponse){
        return  ResponseEntity.ok(servicesResponses.updateResponse(Long.valueOf(id), dtoUpdateResponse));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina la respuesta")
    @Transactional
    public ResponseEntity deleteResponseData(@PathVariable Integer id){
        return  ResponseEntity.ok(servicesResponses.deleteResponse(Long.valueOf(id)));
    }
}
