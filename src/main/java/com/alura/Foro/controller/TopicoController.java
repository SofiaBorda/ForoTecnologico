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

import com.alura.Foro.model.Topico;
import com.alura.Foro.service.TopicoService;

import jakarta.transaction.Transactional;


@RestController
@RequestMapping("/topicos") 
public class TopicoController {
	
	@Autowired
	TopicoService service;
	
	@GetMapping("/por-curso/{cursoId}")
	public ResponseEntity<List<Topico>> getTopicosByCursoId(@PathVariable Long cursoId){
		List<Topico> topicos = service.listarPorCurso(cursoId);
		return ResponseEntity.ok(topicos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<Topico> getTopicoById(@PathVariable Long id){
		Optional<Topico> topicoOptional = service.listarId(id);
		return topicoOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping("/en-curso/{cursoId}")
	public ResponseEntity<Topico> createTopicoEnCurso(@RequestBody Topico t, @PathVariable Long cursoId) {
        Optional<Topico> topicoOptional = service.agregarEnCurso(t, cursoId);
        return topicoOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTopico(@PathVariable Long id) {
        try {
            service.eliminar(id);
            return ResponseEntity.ok("Tópico eliminado exitosamente.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al intentar eliminar el tópico.");
        }
    }
	@GetMapping("/titulo/{palabra}")
	public ResponseEntity<List<Topico>> findByTituloIgnoreCase(@PathVariable String palabra){
		List<Topico> topicos = service.findByTituloIgnoreCase(palabra);
		if (!topicos.isEmpty()) {
	        return ResponseEntity.ok(topicos);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PutMapping("/{id}")
    public ResponseEntity<Topico> updateTopico(@PathVariable Long id, @RequestBody Topico topicoActualizado) {
        Optional<Topico> topicoExistenteOptional = service.listarId(id);

        if (topicoExistenteOptional.isPresent()) {
            Topico topicoExistente = topicoExistenteOptional.get();
            topicoExistente.setTitulo(topicoActualizado.getTitulo());
            topicoExistente.setMensaje(topicoActualizado.getMensaje());

            // Actualizar cualquier otra propiedad que desees permitir que se actualice

            Optional<Topico> topicoActualizadoOptional = service.agregarEnCurso(topicoExistente, id);

            return topicoActualizadoOptional.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
