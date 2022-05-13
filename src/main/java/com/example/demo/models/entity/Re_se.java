package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "re_se")
public class Re_se implements Serializable{

	private static final long serialVersionUID = -6606605786345265863L;
	
	@Id
    @ManyToOne
    @JoinColumn(name = "id_registro") // creamos la columna de tipo objeto con joinColumn
    private Registro registro;

	@Id
    @ManyToOne
    @JoinColumn(name = "id_seccion") // creamos la columna de tipo objeto con joinColumn
    private Seccion seccion;

	public Re_se() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Re_se(Registro registro, Seccion seccion) {
		super();
		this.registro = registro;
		this.seccion = seccion;
	}

	public Registro getRegistro() {
		return registro;
	}

	public void setRegistro(Registro registro) {
		this.registro = registro;
	}

	public Seccion getSeccion() {
		return seccion;
	}

	public void setSeccion(Seccion seccion) {
		this.seccion = seccion;
	}
	
	
}
