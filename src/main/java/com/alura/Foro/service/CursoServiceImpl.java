package com.alura.Foro.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Foro.model.Curso;
import com.alura.Foro.repository.CursoRepository;


@Service
 public class CursoServiceImpl implements CursoService{
	@Autowired
	CursoRepository cursoRepository;

	@Override
    public List<Curso> listar() {
        return cursoRepository.findAll();
    }

    @Override
    public Optional<Curso> listarId(Long id) {
        return cursoRepository.findById(id);
    }

    @Override
    public Optional<Curso> agregar(Curso curso) {
        Curso cursoGuardado = cursoRepository.save(curso);
        return Optional.ofNullable(cursoGuardado);
    }
	
}
