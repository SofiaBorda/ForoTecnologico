package com.alura.Foro.service;

import java.util.List;
import java.util.Optional;

import com.alura.Foro.model.Respuesta;

public interface RespuestaService {
	public Optional<Respuesta> agregarEnTopico(Respuesta r, Long topicoId);
	public List<Respuesta> listarEnTopico(Long topicoId);
	void eliminarRespuesta(Long id);
	Optional<Respuesta> listarById(Long id);
}
