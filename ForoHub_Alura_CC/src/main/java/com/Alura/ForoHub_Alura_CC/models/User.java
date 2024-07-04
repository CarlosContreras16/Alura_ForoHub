package com.Alura.ForoHub_Alura_CC.models;

import com.Alura.ForoHub_Alura_CC.DtoGetData.user.DtoCreateUserToDatabase;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "user")
@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "Id")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Code;
    private String username;
    private String email;
    private String password;

    @JoinColumn(name="type_profile", referencedColumnName = "code")
    @OneToOne
    private  Profile profile;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword(){return this.password;}


    public User(DtoCreateUserToDatabase dtoCreateUserToDatabase){
        this.username = dtoCreateUserToDatabase.username();
        this.email = dtoCreateUserToDatabase.email();
        this.password = dtoCreateUserToDatabase.passwordEncrypted();
        this.profile = dtoCreateUserToDatabase.typeOfProfile();
    }
}
