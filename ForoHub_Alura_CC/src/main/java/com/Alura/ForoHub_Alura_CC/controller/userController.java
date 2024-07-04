package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoCreateUser;
import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoUpdateUser;
import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoUserMoreDetails;
import com.Alura.ForoHub_Alura_CC.Services.ServiceUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class userController {
    @Autowired
    ServiceUser serviceUser;

    @GetMapping
    @Operation(summary = "Encuentra todos los usuarios", tags = "Get")
    public ResponseEntity findAllUser(){
        List<DtoUserMoreDetails> dtoUserMoreDetails = serviceUser.findAllUser();

        return ResponseEntity.ok(dtoUserMoreDetails);
    }

    @GetMapping("{id}")
    @Operation(summary = "Obtiene la información del usuario por el id", tags = "Get")
    public ResponseEntity findUserById(@PathVariable Integer id){
        DtoUserMoreDetails dtoUserMoreDetails = serviceUser.findUserById(Long.valueOf(id));

        return  ResponseEntity.ok(dtoUserMoreDetails);
    }
    @PostMapping
    @Operation(summary = "crea un nuevo usuario", tags = "Post")
    public ResponseEntity createUser(@RequestBody @Valid DtoCreateUser dtoCreateUser, UriComponentsBuilder uriComponentsBuilder){
        DtoUserMoreDetails dtoUserMoreDetails =serviceUser.createNewUser(dtoCreateUser);

        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(dtoUserMoreDetails.code()).toUri();

        return ResponseEntity.created(url).body(dtoUserMoreDetails);
    }

    @PutMapping("{id}")
    @Transactional
    @Operation(summary = "Actualiza la información del usuario", tags = "Put")
    public  ResponseEntity updateUser(@PathVariable Integer id, @RequestBody DtoUpdateUser dtoUpdateUser){

        DtoUserMoreDetails dtoUserMoreDetails = serviceUser.updateUser(Long.valueOf(id), dtoUpdateUser);

        return ResponseEntity.ok(dtoUserMoreDetails);
    }
}
