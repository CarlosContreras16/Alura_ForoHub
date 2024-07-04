package com.Alura.ForoHub_Alura_CC.Services;

import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoCreateUser;
import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoCreateUserToDatabase;
import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoLoginDataUser;
import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoResponseTokenData;
import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoUpdateUser;
import com.Alura.ForoHub_Alura_CC.DtoResponses.user.DtoUserMoreDetails;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.ProfileRepository;
import com.Alura.ForoHub_Alura_CC.dataBaseRepository.UserRepository;
import com.Alura.ForoHub_Alura_CC.models.Profile;
import com.Alura.ForoHub_Alura_CC.models.User;
import com.Alura.ForoHub_Alura_CC.security.TokenService;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

public class ServiceUser {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public DtoResponseTokenData aunthenticateUser(DtoLoginDataUser dtoLoginDataUser){
        UsernamePasswordAuthenticationToken aunthenticationToken = new UsernamePasswordAuthenticationToken(dtoLoginDataUser.username(),
                dtoLoginDataUser.password());
        var userAuthenticate = authenticationManager.authenticate(aunthenticationToken);
        var JWToken = tokenService.generarToken((User) userAuthenticate.getPrincipal());
        DtoResponseTokenData responseTokenData = new DtoResponseTokenData(JWToken,
                "Bearer");

        return  responseTokenData;
    }

    public DtoUserMoreDetails findUserById(Long id){
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()){
            DtoUserMoreDetails dtoUserMoreDetails = new DtoUserMoreDetails(userOptional.get().getCode(),
                    userOptional.get().getUsername(),
                    userOptional.get().getEmail(),
            userOptional.get().getProfile().getName());

            return dtoUserMoreDetails;
        }
        else {
            throw new ValidationException("El tipo de perfil no existe, por favor revisa los perfiles");
        }
    }

    public List<DtoUserMoreDetails> findAllUser(){
        List<User> userRepositoriesList = userRepository.findAll();

        List<DtoUserMoreDetails> dtoUserMoreDetails = userRepositoriesList.stream()
                .map(u -> new DtoUserMoreDetails(u.getCode(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getProfile().getName()))
                .toList();
        return dtoUserMoreDetails;
    }

    public  DtoUserMoreDetails createNewUser(DtoCreateUser dtoCreateUser){
        Optional<Profile> profile = profileRepository.findById(Long.valueOf((dtoCreateUser.typeOfProfile())));

        if (profile.isPresent()){
            DtoCreateUserToDatabase dtoCreateUserToDatabase =  new DtoCreateUserToDatabase(dtoCreateUser.username(),
                    dtoCreateUser.email(),
                    encryptPassword(dtoCreateUser.password()),
                    profile.get());

            User userCreated = new User(dtoCreateUserToDatabase);

            userRepository.save((userCreated));

            DtoUserMoreDetails dtoUserMoreDetails = new DtoUserMoreDetails(userCreated.getCode(),
                    userCreated.getUsername(),
                    userCreated.getEmail(),
                    userCreated.getProfile().getName());
            return dtoUserMoreDetails;
        }
        else {
            throw new ValidationException("El tipo de perfil no existe");
        }
    }
    public DtoUserMoreDetails updateUser(Long id, DtoUpdateUser dtoUpdateUser){
        Optional<Profile> profile = profileRepository.findById(Long.valueOf(dtoUpdateUser.typeOfProfile()));
        Optional<User> userSearched = userRepository.findById(id);

        if (profile.isPresent()){
            if (userSearched.isPresent()){
                User getUser = userSearched.get();

                getUser.setUsername(dtoUpdateUser.username());
                getUser.setEmail(dtoUpdateUser.email());
                getUser.setProfile(profile.get());

                DtoUserMoreDetails dtoUserMoreDetails = new DtoUserMoreDetails(getUser.getCode(),
                        getUser.getUsername(),
                        getUser.getEmail(),
                        getUser.getProfile().getName());

                return dtoUserMoreDetails;
            }
            else {
                throw new ValidationException("El usuario no existe");
            }
        }
        else {
            throw new ValidationException("El tipo de perfil no existe");
        }
    }
    private String encryptPassword(String password)
    {

        return new BCryptPasswordEncoder().encode(password);
    }
}
