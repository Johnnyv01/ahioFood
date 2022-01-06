package com.Projetoifood.domain.model;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
    @Data
    @Entity
	public class FormaPagamento {

    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id;
    	
    	@Column(nullable = false)
    	private String descricao;
        
         
		


		 
		}
		
	


