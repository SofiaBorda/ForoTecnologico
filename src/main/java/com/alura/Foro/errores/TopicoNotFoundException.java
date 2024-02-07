package com.alura.Foro.errores;

public class TopicoNotFoundException extends RuntimeException{
	public TopicoNotFoundException(String mensaje) {
		super(mensaje);
	}
}
