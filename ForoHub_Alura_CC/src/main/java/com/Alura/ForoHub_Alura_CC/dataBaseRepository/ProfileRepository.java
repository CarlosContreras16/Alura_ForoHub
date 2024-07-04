package com.Alura.ForoHub_Alura_CC.dataBaseRepository;

import com.Alura.ForoHub_Alura_CC.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}
