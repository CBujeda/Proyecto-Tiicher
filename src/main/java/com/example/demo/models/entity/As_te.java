package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "as_te")
public class As_te implements Serializable {

	private static final long serialVersionUID = 6783308119189212847L;

	@Id
    @ManyToOne
    @JoinColumn(name = "id_asignatura") // creamos la columna de tipo objeto con joinColumn
    private Asignatura asignatura;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id_tema") // creamos la columna de tipo objeto con joinColumn
    private Tema tema;

	public As_te() {
		super();
		// TODO Auto-generated constructor stub
	}

	public As_te(Asignatura asignatura, Tema tema) {
		super();
		this.asignatura = asignatura;
		this.tema = tema;
	}

	public Asignatura getAsignatura() {
		return asignatura;
	}

	public void setAsignatura(Asignatura asignatura) {
		this.asignatura = asignatura;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	
	
}
