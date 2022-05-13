package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "seccion")
public class Seccion implements Serializable {

	private static final long serialVersionUID = -554441792806765172L;

	@Id
	@GeneratedValue
	private Long id_seccion;
	private String nombre;
	private String contenido;
	
    @ManyToOne
    @JoinColumn(name = "id_tema") // creamos la columna de tipo objeto con joinColumn
    private Tema tema;
    
    @OneToMany(mappedBy = "seccion")   
    private Set<Re_se> re_seList;

	public Seccion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Seccion(Long id_seccion, String nombre, String contenido, Tema tema) {
		super();
		this.id_seccion = id_seccion;
		this.nombre = nombre;
		this.contenido = contenido;
		this.tema = tema;
	}

	public Long getId_seccion() {
		return id_seccion;
	}

	public void setId_seccion(Long id_seccion) {
		this.id_seccion = id_seccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
}
