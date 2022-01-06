package com.Projetoifood.api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.NegocioException;
import com.Projetoifood.domain.expection.RestauranteNaoEncontradoException;
import com.Projetoifood.domain.model.Restaurante;
import com.Projetoifood.domain.repository.RestauranteRepository;
import com.Projetoifood.domain.service.CadastroRestauranteService;


@RestController
@RequestMapping(value ="/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@GetMapping
	public List<Restaurante> todas(){
		return restauranteRepository.findAll();
		
	}
	@GetMapping("/{restaurantesId}")
	  public Restaurante buscar(@PathVariable Long restaurantesId) {
		return cadastroRestauranteService.buscarOuFalhar(restaurantesId);
		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante) {
		
	try {	
   	return cadastroRestauranteService.salvar(restaurante);
	} catch	(RestauranteNaoEncontradoException e) {
		throw new NegocioException(e.getMessage());
	}
			}		
	
		@PutMapping("/{restaurantesId}")
	    public Restaurante atualizar(@PathVariable Long restaurantesId,
	        @RequestBody Restaurante restaurante) {
	     
			Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restaurantesId);
				
			BeanUtils.copyProperties(restaurante, restauranteAtual,
					"id" ,"formasPagamento", "endereco", "dataCadastro", "produtos");
				try {
				return cadastroRestauranteService.salvar(restauranteAtual);
				} catch (RestauranteNaoEncontradoException e) {
					throw new NegocioException(e.getMessage());
				}
					
	        	    }
		
	   
		@PatchMapping("/{restaurantesId}")
		public Restaurante atualizarParcial(@PathVariable Long restaurantesId,
				@RequestBody Map<String, Object> campos){
		Restaurante  restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restaurantesId);
					
		merge(campos, restauranteAtual);
			
		return atualizar (restaurantesId, restauranteAtual);
			
		}
		private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
			camposOrigem.forEach((nomePropriedade,valorPropriedade) -> {
				System.out.println(nomePropriedade + "=" + valorPropriedade);
				});
		}
		
	
	}

  
 


