package com.poo.springjpademo.repository;
import com.poo.springjpademo.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository   extends JpaRepository<Turma, Long> {
}
