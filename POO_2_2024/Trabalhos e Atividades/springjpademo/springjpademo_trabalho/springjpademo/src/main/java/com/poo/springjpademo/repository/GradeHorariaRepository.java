package com.poo.springjpademo.repository;
import com.poo.springjpademo.entity.GradeHoraria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeHorariaRepository  extends JpaRepository<GradeHoraria, Long> {
}
