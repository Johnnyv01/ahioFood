package com.Projetoifood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.Projetoifood.domain.expection.CidadeNaoEncontradoException;
import com.Projetoifood.domain.expection.EntidadeEmUsoException;
import com.Projetoifood.domain.expection.EntidadeNaoEncontradaException;
import com.Projetoifood.domain.model.Cidade;
import com.Projetoifood.domain.model.Estado;
import com.Projetoifood.domain.repository.CidadeRepository;
import com.Projetoifood.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	private static final String MSG_CIDADE_EM_USO =
			"Cidade com o codigo %d não pode ser removido,pois esta em uso";

	private static final String MSG_CIDADE_NAO_ENCONTRADA = 
			"Não existe um codigo com o %d informado";



	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstado;
	
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();

		Estado estado =  cadastroEstado.buscarOuFalhar(estadoId);
	//	Estado estado = estadoRepository.findById(estadoId)
	//		.orElseThrow(() -> new EntidadeNaoEncontradaException(
	//				String.format("Não existe cadastro de estado com código %d", estadoId)));
		
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
}
	
	
	public void remover (Long cidadeId) {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		}catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradoException(
					String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId));
			
		}catch (DataIntegrityViolationException e ) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
	
	public Cidade buscarOuFalhar (Long cidadeId) {
		return cidadeRepository.findById(cidadeId)
				.orElseThrow(() ->  new CidadeNaoEncontradoException(
				String.format(MSG_CIDADE_NAO_ENCONTRADA, cidadeId)));
	}
}


