package com.poo.springjpademo.repository;
import com.poo.springjpademo.entity.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {

}