package br.com.maikelsoft.pedidos.api.domain.exception.handler;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.maikelsoft.pedidos.api.domain.exception.EntidadeNaoEncontradaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<Object> trataEntidadeNaoEncontrada(EntidadeNaoEncontradaException ex, WebRequest request){
		return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
		Object mensagem;
		if(body == null) {
			mensagem = status.getReasonPhrase();
		} else {
			mensagem = body;
		}
		body = Problema.builder()
				.dataHora(LocalDateTime.now())
				.mensagem(mensagem.toString())
				.build();
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}
}
