package com.Alura.ForoHub_Alura_CC.dataBaseRepository;

import com.Alura.ForoHub_Alura_CC.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Long> {
}
