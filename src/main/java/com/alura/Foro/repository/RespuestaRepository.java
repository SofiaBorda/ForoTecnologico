package com.alura.Foro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alura.Foro.model.Respuesta;

@Repository
public interface RespuestaRepository extends JpaRepository<Respuesta, Long>{
	public List<Respuesta> findByTopicoId(Long topicoId);
}
