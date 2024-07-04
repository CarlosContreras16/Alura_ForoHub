package com.Alura.ForoHub_Alura_CC.dataBaseRepository;

import com.Alura.ForoHub_Alura_CC.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
