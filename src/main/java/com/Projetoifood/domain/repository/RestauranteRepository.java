package com.Projetoifood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.model.Restaurante;

 public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>,RestauranteRepositoryCustomized,
  JpaSpecificationExecutor<Restaurante> {

	List<Restaurante> queryByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

	//@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinha);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long Cozinha);
	
	
	Optional<Restaurante> findFirstRestauranteByNomeContaining(String nome);

	List<Restaurante> findTop2RestauranteByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinha);
	
   
}
