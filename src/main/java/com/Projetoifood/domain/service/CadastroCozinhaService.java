package com.Projetoifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Projetoifood.domain.expection.CozinhaNaoEncontradaException;
import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.model.Cozinha;
import com.Projetoifood.domain.repository.CozinhaRepository;



@Service
public class CadastroCozinhaService {

	private static final String MSG_COZINHA_EM_USO = "Cozinha de código %d não pode ser removida, pois está em uso";
	private static final String MSG_COZINHA_NAO_ENCONTRADA = "Não existe um cadastro de cozinha com código %d";
	
	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	public Cozinha adicionar (Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	
	public void excluir(Long cozinhaId) {
		try {
			cozinhaRepository.deleteById(cozinhaId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(
				String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId));
		
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
				String.format(MSG_COZINHA_EM_USO, cozinhaId));
		}
	}
	
	 public Cozinha buscarOufalhar(Long cozinhaId) {
		 return cozinhaRepository.findById(cozinhaId)
				 .orElseThrow(()  -> new CozinhaNaoEncontradaException(
		 	 String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
	 }
	
	
	}
	
