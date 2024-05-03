package com.poo.springjpademo.repository;
import com.poo.springjpademo.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository   extends JpaRepository<Sala, Long> {
}
