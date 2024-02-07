package com.alura.Foro.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alura.Foro.errores.TopicoNotFoundException;
import com.alura.Foro.model.Respuesta;
import com.alura.Foro.model.Topico;
import com.alura.Foro.repository.RespuestaRepository;
import com.alura.Foro.repository.TopicoRepository;

@Service
public class RespuestaServiceImpl implements RespuestaService{

	@Autowired
	TopicoRepository topicoRepository;
	@Autowired
	RespuestaRepository respuestaRepository;
	@Override
	public Optional<Respuesta> agregarEnTopico(Respuesta r, Long topicoId) {
		Topico topico = topicoRepository.findById(topicoId)
                .orElseThrow(() -> new TopicoNotFoundException("Tópico no encontrado con ID: " + topicoId));

        r.setTopico(topico);
        Respuesta respuestaGuardada = respuestaRepository.save(r);
        return Optional.ofNullable(respuestaGuardada);
	}
	@Override
	public List<Respuesta> listarEnTopico(Long topicoId) {
		return respuestaRepository.findByTopicoId(topicoId);
	}
	@Override
	public void eliminarRespuesta(Long id) {
		respuestaRepository.deleteById(id);
	}
	@Override
	public Optional<Respuesta> listarById(Long id) {
		return respuestaRepository.findById(id);
	}
	
}
