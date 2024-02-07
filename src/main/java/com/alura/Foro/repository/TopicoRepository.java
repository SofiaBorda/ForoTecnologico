package com.alura.Foro.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.Foro.model.Topico;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long>{

	List<Topico> findByCursoId(Long cursoId);
	List<Topico> findByTituloIgnoreCase(String palabra);
}
