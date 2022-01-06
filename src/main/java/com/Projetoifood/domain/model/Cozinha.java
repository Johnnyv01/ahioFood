package com.Projetoifood.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    @Entity
	public class Cozinha {

		@Id
		@EqualsAndHashCode.Include
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		
		//@JsonIgnore //ignora os atributos
		//@JsonProperty("Titulo") //property sobreEscreve a string nome
		
		@Column(nullable = false)
		private String nome;
		
		@JsonIgnore
		@OneToMany(mappedBy = "cozinha")
		private List<Restaurante> restaurantes = new ArrayList<>();
    
         
		
	}


