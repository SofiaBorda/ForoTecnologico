package com.alura.Foro.errores;

public class CursoNotFoundException extends RuntimeException{
	public CursoNotFoundException(String mensaje) {
		super(mensaje);
	}
}
