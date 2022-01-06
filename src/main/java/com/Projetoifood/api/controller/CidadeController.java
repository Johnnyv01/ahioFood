package com.Projetoifood.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.EstadoNaoEncontradoException;
import com.Projetoifood.domain.expection.NegocioException;
import com.Projetoifood.domain.model.Cidade;
import com.Projetoifood.domain.repository.CidadeRepository;
import com.Projetoifood.domain.service.CadastroCidadeService;

@RestController
@RequestMapping(value = "/cidades")
public class CidadeController {
		
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;

	
	@GetMapping
	public List<Cidade> todas(){
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade  buscar(@PathVariable Long cidadeId){
		return cadastroCidadeService.buscarOuFalhar(cidadeId);
				
	}
	
	@PostMapping
	@ResponseStatus (value = HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) {
		try {
		 return cadastroCidadeService.salvar(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException (e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public Cidade atualizar(@PathVariable Long cidadeId,
			@RequestBody Cidade cidade) {
		Cidade cidadeAtual = cadastroCidadeService.buscarOuFalhar(cidadeId);
		
					BeanUtils.copyProperties(cidade, cidadeAtual, "id");
				
					try {
				return  cadastroCidadeService.salvar(cidadeAtual);
					} catch (EstadoNaoEncontradoException e) {
						throw new NegocioException(e.getMessage());
					}	
			}
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value = HttpStatus.CONFLICT)
	public void remover(@PathVariable Long cidadeId) {
		
			cadastroCidadeService.remover(cidadeId);	
			
		
	}

}
