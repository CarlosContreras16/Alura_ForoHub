package com.Alura.ForoHub_Alura_CC.security;

import com.Alura.ForoHub_Alura_CC.dataBaseRepository.UserRepository;
import com.Alura.ForoHub_Alura_CC.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User loadUserByUsername(String username) throws UsernameNotFoundException{
        return  userRepository.findByUsername(username);
    }


}
