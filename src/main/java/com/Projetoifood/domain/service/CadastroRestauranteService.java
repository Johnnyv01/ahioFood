package com.Projetoifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.RestauranteNaoEncontradoException;
import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.model.Restaurante;
import com.Projetoifood.domain.repository.CozinhaRepository;
import com.Projetoifood.domain.repository.RestauranteRepository;



@Service
public class CadastroRestauranteService {

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO 
	= "Não existe um cadastro de restaurante com código %d";

@Autowired
private RestauranteRepository restauranteRepository;

@Autowired
private CadastroCozinhaService cadastroCozinhaService;

public Restaurante salvar(Restaurante restaurante) {
	Long cozinhaId = restaurante.getCozinha().getId();
	
	Cozinha cozinha = cadastroCozinhaService.buscarOufalhar(cozinhaId);
	
//	Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//		.orElseThrow(() -> new EntidadeNaoEncontradaException(
//				String.format("Não existe cadastro de cozinha com código %d", cozinhaId)));
	
	restaurante.setCozinha(cozinha);
	
	return restauranteRepository.save(restaurante);
}

public Restaurante buscarOuFalhar(Long restauranteId) {
	return restauranteRepository.findById(restauranteId)
		.orElseThrow(() -> new RestauranteNaoEncontradoException(
				String.format(MSG_RESTAURANTE_NAO_ENCONTRADO, restauranteId)));
}


}
	
