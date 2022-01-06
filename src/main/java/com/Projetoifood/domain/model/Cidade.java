package com.Projetoifood.domain.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import com.Projetoifood.domain.model.Estado;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Data
    @Entity
	public class Cidade {

		
		@EqualsAndHashCode.Include
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		
		@Column(nullable = false)
		private String nome;

		
		@ManyToOne
        @JoinColumn(nullable = false)
        private Estado estado;
         

		
		}

		

	
		

		

		
	


