package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "curso")
public class Curso implements Serializable{

	private static final long serialVersionUID = 8073969306655409501L;

	@Id
	@GeneratedValue
	private Long id_curso;
	private String nombre_curso;
		
    @OneToMany(mappedBy = "curso",orphanRemoval = true)   
    private Set<Clase> claseList; 
    
	public Curso() {
		super();
	}

	public Curso(Long id_curso, String nombre_curso) {
		super();
		this.id_curso = id_curso;
		this.nombre_curso = nombre_curso;
	}

	public Long getId_curso() {
		return id_curso;
	}

	public void setId_curso(Long id_curso) {
		this.id_curso = id_curso;
	}

	public String getNombre_curso() {
		return nombre_curso;
	}

	public void setNombre_curso(String nombre_curso) {
		this.nombre_curso = nombre_curso;
	}
	
}
