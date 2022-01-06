package com.Projetoifood.api.controller;

import static com.Projetoifood.infrastructure.repository.spec.RestauranteSpecs.comFreteGratis;
import static com.Projetoifood.infrastructure.repository.spec.RestauranteSpecs.comNomeSemelhante;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.model.Restaurante;
import com.Projetoifood.domain.repository.CozinhaRepository;
import com.Projetoifood.domain.repository.RestauranteRepository;



@RestController
@RequestMapping("/teste")
public class TesteController {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/cozinhas/por-nome")
	public List<Cozinha> cozinhasPorNome(String nome) {
		return cozinhaRepository.findTodasByNomeContaining(nome);
	
}
	@GetMapping("/cozinhas/unico/por-nome")
	public Optional<Cozinha> cozinhaPorNome(String nome) {
		return cozinhaRepository.findByNome(nome);
}
	@GetMapping("/cozinhas/exists")
	public boolean cozinhaExists (String nome) {
		return cozinhaRepository.existsByNome(nome);
}
	@GetMapping("/cozinhas/primeira")
	public Optional <Cozinha> cozinhaPorNome () {
		return cozinhaRepository.buscarPrimeiro();
}	
	
	
	@GetMapping("/restaurantes/por-taxa-frete")
	public List<Restaurante> restaurantesPorTaxaFrete(
			BigDecimal taxaInicial, BigDecimal taxaFinal) {
		return restauranteRepository.queryByTaxaFreteBetween(taxaInicial, taxaFinal);
}
	@GetMapping("/restaurantes/por-nome")
	public List<Restaurante> restaurantesPorTaxaFrete(
		String nome, Long cozinhaId) {
		return restauranteRepository.consultarPorNome(nome, cozinhaId);
}
	@GetMapping("/restaurantes/primeiro-por-nome")
	public Optional<Restaurante> restaurantesFirstName(	String nome) {
			return restauranteRepository.findFirstRestauranteByNomeContaining(nome);
}
	@GetMapping("/restaurantes/dois-primeiros-por-nome")
	public List<Restaurante> restaurantesTop2tName(	String nome) {
			return restauranteRepository.findTop2RestauranteByNomeContaining(nome);
	}		
	
	@GetMapping("/restaurantes/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome, 
	BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
	return restauranteRepository.consultar(nome, taxaFreteInicial, taxaFreteFinal);
	
	
}
	
	@GetMapping("/restaurantes/count-por-cozinha")
	public int  restaurantesCountById(	Long cozinhaId) {
			return restauranteRepository.countByCozinhaId(cozinhaId);
}
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restaurantesComFreteGratis(String nome) {
	return restauranteRepository.findComFreteGratis(nome);
	
  }
	@GetMapping("/restaurantes/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}
	
}
