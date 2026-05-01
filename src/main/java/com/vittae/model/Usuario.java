package com.vittae.model;

import com.vittae.model.enums.Perfil;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cpf;
	private String senha;
	private String nome;
	private String email;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;


}