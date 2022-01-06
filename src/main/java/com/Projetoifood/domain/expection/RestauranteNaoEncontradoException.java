package com.Projetoifood.domain.expection;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RestauranteNaoEncontradoException extends EstadoNaoEncontradoException {

	private static final long serialVersionUID = 1L;

	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
		public RestauranteNaoEncontradoException(Long estadoId) {
			this(String.format("Não existe um cadastro de estado com o codigo %d", estadoId));
		}
		
		
	}
	
