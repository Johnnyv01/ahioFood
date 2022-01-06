package com.Projetoifood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.expection.EstadoNaoEncontradoException;
import com.Projetoifood.domain.model.Cidade;
import com.Projetoifood.domain.model.Estado;
import com.Projetoifood.domain.repository.CidadeRepository;
import com.Projetoifood.domain.repository.EstadoRepository;

@Service
public class CadastroEstadoService {
	
	private static final String MSG_ESTADO_EM_USO = "Estado de codigo %d não pode ser removido,pois estado em uso";
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	
	public Estado adicionar(Estado estado) {
		return estadoRepository.save(estado);
		}
	
	
	public void remover (Long estadoId) {
		try {
			estadoRepository.deleteById(estadoId);
			
		}catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
			
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
		
		}
	public Estado buscarOuFalhar(Long estadoId) {
		return estadoRepository.findById(estadoId)
			.orElseThrow(() -> new EstadoNaoEncontradoException(estadoId));
	}
}

	
	
	
 

