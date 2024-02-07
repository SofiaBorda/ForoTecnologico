package com.alura.Foro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Foro.errores.CursoNotFoundException;
import com.alura.Foro.model.Curso;
import com.alura.Foro.model.Topico;
import com.alura.Foro.repository.CursoRepository;
import com.alura.Foro.repository.TopicoRepository;

@Service
public class TopicoServiceImpl implements TopicoService{
	
	@Autowired
	TopicoRepository repo;
	@Autowired
	private CursoRepository cursoRepository;

	@Override
	public Optional<Topico> listarId(Long id) {
		return repo.findById(id);
	}
	@Override
	public List<Topico> listarPorCurso(Long cursoId) {
		return repo.findByCursoId(cursoId);
	}
	@Override
	public Optional<Topico> agregarEnCurso(Topico t, Long cursoId) {
		Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new CursoNotFoundException("Curso no encontrado con ID: " + cursoId));

        t.setCurso(curso);
        Topico topicoGuardado = repo.save(t);
        return Optional.ofNullable(topicoGuardado);
	}
	@Override
	public void eliminar(Long id) {
		repo.deleteById(id);
	}
	@Override
	public List<Topico> findByTituloIgnoreCase(String palabra) {
		return repo.findByTituloIgnoreCase(palabra);
	}
}
