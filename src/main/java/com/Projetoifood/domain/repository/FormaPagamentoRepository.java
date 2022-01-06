package com.Projetoifood.domain.repository;

import java.util.List;

import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

	List<FormaPagamento> todas ();
	FormaPagamento porId (Long id);
	FormaPagamento adicionar (FormaPagamento formaPagamento);
	void remover (FormaPagamento formaPagamento);
	
	
}
