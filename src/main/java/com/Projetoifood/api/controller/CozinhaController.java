package com.Projetoifood.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.Projetoifood.domain.expection.CozinhaNaoEncontradaException;
import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.NegocioException;
import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.repository.CozinhaRepository;
import com.Projetoifood.domain.service.CadastroCozinhaService;


@RestController
@RequestMapping(value ="/cozinhas")
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@GetMapping
	public List<Cozinha> todas(){
		
		return cozinhaRepository.findAll();
		
		
	}
	
	//@ResponseStatus(HttpStatus.CREATED) //mudando status da resposta http
	@GetMapping ("/{cozinhaId}")
	public Cozinha buscar(@PathVariable Long cozinhaId) {
		return cadastroCozinhaService.buscarOufalhar(cozinhaId);
		
		/*Optional <Cozinha> cozinha = cozinhaRepository.findById(cozinhaId);
		
		if (cozinha.isPresent()) {
			return ResponseEntity.ok(cozinha.get());
		}
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();*/
		//return ResponseEntity.notFound().build();
	}
	
	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		 try {
		return cadastroCozinhaService.adicionar(cozinha);
		 }catch (CozinhaNaoEncontradaException e) {
			 throw new NegocioException(e.getMessage());
		 }
	 }
	 
	 @PutMapping("/{cozinhaId}")
		public Cozinha atualizar(@PathVariable Long cozinhaId,
				@RequestBody Cozinha cozinha) {
		 Cozinha cozinhaAtual = cadastroCozinhaService.buscarOufalhar(cozinhaId);
		 
			
				BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
				
				try {
				return cadastroCozinhaService.adicionar(cozinhaAtual);
				} catch (CozinhaNaoEncontradaException e) {
					throw new NegocioException(e.getMessage());
				}
	 }
			


	// @DeleteMapping("/{cozinhaId}")
		//public ResponseEntity<Cozinha> remover(@PathVariable Long cozinhaId) {
		//	try {
				
				//	cadastroCozinhaService.excluir(cozinhaId);
			//			return ResponseEntity.noContent().build();
					
			//	}catch (EntidadeNaoEncontradaException e) {
		//			return ResponseEntity.notFound().build();
			//	
		//	} catch (EntidadeEmUsoException e) {
//				return ResponseEntity.status(HttpStatus.CONFLICT).build();
		//	}
		
	  

@DeleteMapping("/{cozinhaId}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void remover(@PathVariable Long cozinhaId) {
	cadastroCozinhaService.excluir(cozinhaId);
}


}

