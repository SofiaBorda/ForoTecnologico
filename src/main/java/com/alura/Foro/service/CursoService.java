package com.alura.Foro.service;

import java.util.List;
import java.util.Optional;

import com.alura.Foro.model.Curso;

public interface CursoService {
	public List<Curso> listar();
    public Optional<Curso> listarId(Long id);
    public Optional<Curso> agregar(Curso curso);
}
