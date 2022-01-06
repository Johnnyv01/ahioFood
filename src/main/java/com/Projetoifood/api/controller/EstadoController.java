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

import com.Projetoifood.domain.expection.CidadeNaoEncontradoException;
import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.NegocioException;
import com.Projetoifood.domain.model.Estado;
import com.Projetoifood.domain.repository.EstadoRepository;
import com.Projetoifood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;
    
    @Autowired
    private CadastroEstadoService cadastroEstado;
    
    @GetMapping
    public List<Estado> todos() {
        return estadoRepository.findAll();
    }
    
    @GetMapping("/{estadoId}")
    public Estado buscar(@PathVariable Long estadoId){
    	 return cadastroEstado.buscarOuFalhar(estadoId);
    	
    }
    
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Estado adicionar (@RequestBody Estado estado) {
    	
    	try {
    	return cadastroEstado.adicionar(estado);
    	}catch(CidadeNaoEncontradoException e) {
    		throw new  NegocioException (e.getMessage());
    	}
    		
    	
    }
    
    @PutMapping("/{estadoId}")
    public Estado remover(@PathVariable Long estadoId,
    		@RequestBody Estado estado){
    	Estado estadoAtual = cadastroEstado.buscarOuFalhar(estadoId);
    	
			BeanUtils.copyProperties(estado, estadoAtual, "id");
    		try {
    		return cadastroEstado.adicionar(estadoAtual);
    		}catch (CidadeNaoEncontradoException e) {
    			throw new NegocioException(e.getMessage());
    		}
    }
    
    
    @DeleteMapping("/{estadoId}")
	public ResponseEntity<?> remover(@PathVariable Long estadoId) {
		try {
			cadastroEstado.remover(estadoId);	
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body(e.getMessage());
		}
    }
}
