package com.poo.springjpademo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class GradeHoraria {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @ManyToOne
    private Horario primeiroHorario;

    @ManyToOne
    private Horario segundoHorario;

    public GradeHoraria(Horario primeiroHorario, Horario segundoHorario){
        this.primeiroHorario = primeiroHorario;
        this.segundoHorario = segundoHorario;
    }
}
