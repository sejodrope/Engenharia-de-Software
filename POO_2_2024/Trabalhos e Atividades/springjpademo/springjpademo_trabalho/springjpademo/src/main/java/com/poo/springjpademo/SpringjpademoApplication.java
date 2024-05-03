// =============================
package com.poo.springjpademo;

import com.poo.springjpademo.entity.*;
import com.poo.springjpademo.repository.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringjpademoApplication {

	private static final Logger log = LoggerFactory.getLogger(SpringjpademoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(SpringjpademoApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(DiaDaSemanaRepository diaDaSemanaRepository,
								  DisciplinaRepository disciplinaRepository,
								  GradeHorariaRepository gradeHorariaRepository,
								  HorarioRepository horarioRepository,
								  PeriodoRepository periodoRepository,
								  ProfessorRepository professorRepository,
								  SalaRepository salaRepository,
								  TurmaRepository turmaRepository){

		return (args) -> {
			log.info("-------------- SALVANDO DIAS DA SEMANA --------------");
			diaDaSemanaRepository.save(new DiaDaSemana("Segunda-feira"));
			diaDaSemanaRepository.save(new DiaDaSemana("Terça-feira"));
			diaDaSemanaRepository.save(new DiaDaSemana("Quarta-feira"));
			diaDaSemanaRepository.save(new DiaDaSemana("Quinta-feira"));
			diaDaSemanaRepository.save(new DiaDaSemana("Sexta-feira"));
			log.info("Inserts concluídos.");

			log.info("-------------- [findAll] LISTAR DIAS DA SEMANA --------------");
			for(var linha : diaDaSemanaRepository.findAll()){
				log.info(linha.getNome());
			}

			log.info("-------------- SALVANDO PROFESSORES --------------");
			professorRepository.save(new Professor("Leanderson Andre"));
			professorRepository.save(new Professor("Diana dos Santos"));
			professorRepository.save(new Professor("Marcelo Pereira"));
			professorRepository.save(new Professor("Leila Techio"));
			professorRepository.save(new Professor("Vanessa Camargo"));
			log.info("Inserts concluídos.");

			log.info("-------------- [findAll] LISTAR PROFESSORES --------------");
			for(var linha : professorRepository.findAll()){
				log.info(linha.getNome());
			}

				log.info("-------------- SALVANDO DISCIPLINAS --------------");
				var prof01 = professorRepository.findById(1L);
				disciplinaRepository.save(new Disciplina("Programação Orientada  à Objetos II", prof01.get()));
				var prof02 = professorRepository.findById(2L);
				disciplinaRepository.save(new Disciplina("Eixo Institucional IV", prof02.get()));
				var prof03 = professorRepository.findById(3L);
				disciplinaRepository.save(new Disciplina("Sistemas Operacionais", prof03.get()));
				var prof04 = professorRepository.findById(4L);
				disciplinaRepository.save(new Disciplina("Vivências de Extensão III", prof04.get()));
				var prof05 = professorRepository.findById(5L);
				disciplinaRepository.save(new Disciplina("Ética e Legislação em Computação", prof05.get()));
				log.info("Inserts concluídos.");
				log.info("-------------- [findAll] LISTAR DISCIPLINAS --------------");
				for(var linha : disciplinaRepository.findAll()){
					log.info("ID: " + linha.getId() + " | NOME DISC.: " + linha.getNome() + " | PROFESSOR: " + linha.getProfessor().getNome());
				}

			log.info("-------------- SALVANDO TURMAS --------------");
			turmaRepository.save(new Turma("4º Semestre"));
			log.info("Inserts concluídos.");
			log.info("-------------- [findAll] LISTAR TURMAS --------------");
			for(var linha : turmaRepository.findAll()){
				log.info("ID: " + linha.getId() + " | SEMESTRE: " + linha.getSemestre());
			}

			log.info("-------------- SALVANDO SALAS --------------");
			salaRepository.save(new Sala("C323"));
			salaRepository.save(new Sala("C331"));
			salaRepository.save(new Sala("C115"));
			salaRepository.save(new Sala("C316"));
			salaRepository.save(new Sala("C311"));
			log.info("Inserts concluídos.");
			log.info("-------------- [findAll] LISTAR SALAS --------------");
			for(var linha : salaRepository.findAll()){
				log.info("ID: " + linha.getId() + " | CÓDIGO: " + linha.getCodigoDaSala());
			}

			log.info("-------------- SALVANDO PERIODOS --------------");
			periodoRepository.save(new Periodo("Primeiro | 18:55 às 20:30"));
			periodoRepository.save(new Periodo("Segundo | 20:55 às 22:30"));
			log.info("Inserts concluídos.");
			log.info("-------------- [findAll] LISTAR PERIODOS --------------");
			for(var linha : periodoRepository.findAll()){
				log.info("ID: " + linha.getId() + " | CÓDIGO: " + linha.getDescricao());
			}

			log.info("-------------- SALVANDO HORARIOS --------------");
			// Aqui poderia pegar o professor direto por disciplina (e outras infos no geral),
			// mas quisemos deixar bem mastigadinho pra não errar


			// Salvando primeiro periodo do dia
			var professor01 = professorRepository.findById(5L);
			var dia01 = diaDaSemanaRepository.findById(3L);
			var turma01 = turmaRepository.findById(1L);
			var periodo01 = periodoRepository.findById(1L);
			var disciplina01 = disciplinaRepository.findById(5L);
			var sala01 = salaRepository.findById(5L);
			horarioRepository.save(new Horario("29/04/2024", turma01.get(), dia01.get(), periodo01.get(),
													disciplina01.get(), professor01.get(), sala01.get()));

			// Salvando segundo periodo do dia
			var professor02 = professorRepository.findById(1L);
			// dia da semana será o mesmo
			// turma será a mesma
			var periodo02 = periodoRepository.findById(2L);
			var disciplina02 = disciplinaRepository.findById(1L);
			var sala02 = salaRepository.findById(1L);
			horarioRepository.save(new Horario("29/04/2024", turma01.get(), dia01.get(), periodo02.get(),
					disciplina02.get(), professor02.get(), sala02.get()));
			log.info("Inserts concluídos.");
			log.info("-------------- [findAll] LISTAR HORARIOS --------------");
			for(var linha : horarioRepository.findAll()){
				log.info(linha.toString());
			}
			log.info("-------------- [findAll] LISTAR HORARIOS POR DATA --------------");
			// Só pode haver dois horários na mesma data pra mesma turma, mas essa é uma
			// validação que precisaria implementar e como são poucos INSERTs pulei pra simplificar
			List<Horario> horarios = horarioRepository.findAllByData("29/04/2024");
			for (Horario horario : horarios) {
				log.info(horario.toString());
			}
			log.info("-------------- SALVANDO GRADE DE HORARIOS 01 --------------");
			var horario01 = horarioRepository.findById(1L);
			var horario02 = horarioRepository.findById(2L);

			log.info("Variável horario01 = " + horario01);
			log.info("Variável horario02 = " + horario02);

			log.info("Inserts concluídos.");
			gradeHorariaRepository.save(new GradeHoraria(horario01.get(), horario02.get()));
			log.info("-------------- [findAll] + Formatação | LISTAR GRADE DE HORARIOS --------------");
			for(var linha : gradeHorariaRepository.findAll()){
				log.info("//////// GRADE HORARIA DE "+ linha.getPrimeiroHorario().getData() + " ////////");
				log.info("======== Horario: 01 ========");
				log.info("TURMA: " + linha.getPrimeiroHorario().getTurma().getSemestre());
				log.info("DIA DA SEMANA: " + linha.getPrimeiroHorario().getDiaDaSemana().getNome());
				log.info("PERÍODO: " + linha.getPrimeiroHorario().getPeriodo().getDescricao());
				log.info("DISCIPLINA: " + linha.getPrimeiroHorario().getDisciplina().getNome());
				log.info("PROFESSOR: " + linha.getPrimeiroHorario().getProfessor().getNome());
				log.info("SALA: " + linha.getPrimeiroHorario().getSala().getCodigoDaSala());
				log.info("======== Horario: 02 ========");
				log.info("TURMA: " + linha.getSegundoHorario().getTurma().getSemestre());
				log.info("DIA DA SEMANA: " + linha.getSegundoHorario().getDiaDaSemana().getNome());
				log.info("PERÍODO: " + linha.getSegundoHorario().getPeriodo().getDescricao());
				log.info("DISCIPLINA: " + linha.getSegundoHorario().getDisciplina().getNome());
				log.info("PROFESSOR: " + linha.getSegundoHorario().getProfessor().getNome());
				log.info("SALA: " + linha.getSegundoHorario().getSala().getCodigoDaSala());
			}
		;};
	}

}
