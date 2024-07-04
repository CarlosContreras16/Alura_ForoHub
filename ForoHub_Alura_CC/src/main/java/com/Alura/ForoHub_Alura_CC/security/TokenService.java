package com.Alura.ForoHub_Alura_CC.security;

import com.Alura.ForoHub_Alura_CC.models.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("api.security.secret")
    private String apiSecret;

    public String generarToken(User userParameter){

        try {
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            String token = JWT.create()
                    .withIssuer("AluraChallenge")
                    .withSubject(userParameter.getUsername())
                    .withClaim("id", userParameter.getCode())
                    .withExpiresAt(generarDateTimeExpired())
                    .sign(algorithm);

            return token;
        }catch (JWTCreationException exception){
            throw new RuntimeException();
        }
    }

    public String getSubject(String token)
    {
        try {
            if (token == null)
            {
                throw new RuntimeException();
            }

            DecodedJWT decodedJWT;
            Algorithm algorithm = Algorithm.HMAC256(apiSecret);
            JWTVerifier verifier = JWT.require(algorithm)
                    //especifica cualquier validacion
                    .withIssuer("AluraChallenge")
                    //reusable verifier instance
                    .build();

            decodedJWT = verifier.verify(token);

            if (decodedJWT.getSubject() != null)
            {
                return decodedJWT.getSubject();
            }
            else {
                return null;
            }
        }
        catch (JWTCreationException exception){
            return null;
        }
    }
    //crea un tokem por 7 dias
    private Instant generarDateTimeExpired(){
        return LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.of("-06:00"));
    }
}
