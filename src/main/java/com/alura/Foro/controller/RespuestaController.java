package com.alura.Foro.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alura.Foro.model.Respuesta;
import com.alura.Foro.service.RespuestaService;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {
	@Autowired
	RespuestaService respuestaService;
	@PostMapping("/en-topico/{topicoId}")
    public ResponseEntity<Respuesta> createRespuestaEnTopico(@RequestBody Respuesta r, @PathVariable Long topicoId) {
        Optional<Respuesta> respuestaOptional = respuestaService.agregarEnTopico(r, topicoId);
        return respuestaOptional.map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
	@GetMapping("/por-topico/{topicoId}")
	public ResponseEntity<List<Respuesta>> getRespuestasByTopicoId(@PathVariable Long topicoId){
		List<Respuesta> respuestas = respuestaService.listarEnTopico(topicoId);
		return ResponseEntity.ok(respuestas);
	}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteRespuesta(@PathVariable Long id){
		try {
			respuestaService.eliminarRespuesta(id);
			return ResponseEntity.ok("Respuesta eliminada exitosamente");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al intentar eliminar la respuesta.");
		}
	}
	@PutMapping("update/{id}")
	public ResponseEntity<Respuesta> updateRespuesta(@PathVariable Long id, @RequestBody Respuesta respuestaActualizada){
		Optional<Respuesta> respuestaExistenteOptional = respuestaService.listarById(id);
		if (respuestaExistenteOptional.isPresent()) {
			Respuesta respuestaExistente = respuestaExistenteOptional.get();
			respuestaExistente.setMensaje(respuestaActualizada.getMensaje());
			respuestaExistente.setTopico(respuestaActualizada.getTopico());
			Optional<Respuesta> respuestaActualizadaOptional = respuestaService.agregarEnTopico(respuestaExistente, id);
			return respuestaActualizadaOptional.map(ResponseEntity::ok).orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}
