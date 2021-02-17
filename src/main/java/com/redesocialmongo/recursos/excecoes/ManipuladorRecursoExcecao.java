package com.redesocialmongo.recursos.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.redesocialmongo.servico.excecao.ObjetoNaoEncontradoExcecao;

@ControllerAdvice
public class ManipuladorRecursoExcecao {

	@ExceptionHandler(ObjetoNaoEncontradoExcecao.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoExcecao e, HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		ErroPadrao ep = new ErroPadrao(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(ep);
	}
}
