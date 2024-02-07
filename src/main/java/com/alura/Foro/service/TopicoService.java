package com.alura.Foro.service;

import java.util.List;
import java.util.Optional;

import com.alura.Foro.model.Topico;

public interface TopicoService {
    public Optional<Topico> listarId(Long id);
    public void eliminar(Long id);
    public List<Topico> listarPorCurso(Long cursoId);
	public Optional<Topico> agregarEnCurso(Topico t, Long cursoId);
	public List<Topico> findByTituloIgnoreCase(String palabra);
}
