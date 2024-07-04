package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoLoginDataUser;
import com.Alura.ForoHub_Alura_CC.Services.ServiceUser;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    ServiceUser serviceUser;

    @PostMapping
    @Operation(summary = "Receive the username and password and it returns a JWT with authentication data", tags = "Authentication")
    public ResponseEntity checkAuthentication(@RequestBody DtoLoginDataUser dtoLoginDataUser){
        return ResponseEntity.ok(serviceUser.aunthenticateUser(dtoLoginDataUser));
    }
}
