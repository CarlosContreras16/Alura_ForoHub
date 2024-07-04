package com.Alura.ForoHub_Alura_CC.controller;

import com.Alura.ForoHub_Alura_CC.DtoResponses.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

//    @PostMapping
//    public ResponseEntity autenticarUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
//        Authentication authToken = new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.login(),
//                datosAutenticacionUsuario.clave());
//        var usuarioAuntenticado = authenticationManager.authenticate((authToken));
//        var JWToken = tokenService.generarToken((Usuario) usuarioAuntenticado.getPrincipal());
//        return ResponseEntity.ok(new DatosJWTToken(JWToken));
//    }
}
