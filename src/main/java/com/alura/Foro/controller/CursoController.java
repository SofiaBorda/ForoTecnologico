package com.alura.Foro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.Foro.model.Curso;
import com.alura.Foro.model.Topico;
import com.alura.Foro.service.CursoService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/cursos") 
public class CursoController {
	@Autowired
	CursoService service;
	
	@GetMapping
	public ResponseEntity<List<Curso>> getCursos(){
		List<Curso> cursos = service.listar();
		return ResponseEntity.ok(cursos);
	}
	@PostMapping
	public ResponseEntity<Curso> createTopico(@RequestBody Curso c){
		Optional<Curso> cursoOptional = service.agregar(c);
		return cursoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
	}
}
