package com.poo.springjpademo.repository;
import com.poo.springjpademo.entity.Disciplina;
import com.poo.springjpademo.entity.Horario;
import com.poo.springjpademo.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HorarioRepository  extends JpaRepository<Horario, Long> {

    List<Horario> findAllByData(String data);
}
