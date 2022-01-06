package com.Projetoifood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.Projetoifood.domain.model.Restaurante;

public interface RestauranteRepositoryCustomized {

	List<Restaurante> consultar(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	List<Restaurante> findComFreteGratis(String nome);

	

}