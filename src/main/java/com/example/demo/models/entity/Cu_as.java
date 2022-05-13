package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "cu_as")
public class Cu_as implements Serializable {

	private static final long serialVersionUID = -3036299121720863769L;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id_curso") // creamos la columna de tipo objeto con joinColumn
    private Curso curso;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id_asignatura") // creamos la columna de tipo objeto con joinColumn
    private Asignatura asignatura;

	public Cu_as() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cu_as(Curso curso, Asignatura asignatura) {
		super();
		this.curso = curso;
		this.asignatura = asignatura;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}
	
	
	
}
