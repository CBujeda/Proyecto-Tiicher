package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tema")
public class Tema implements Serializable {

	private static final long serialVersionUID = 4025901505987104495L;
	@Id
	@GeneratedValue
	private Long id_tema;
	private String nombre;
	private String contenido;
	
	
    @OneToMany(mappedBy = "tema")   
    private Set<As_te> as_teList;

    @OneToMany(mappedBy = "tema")   
    private Set<Seccion> seccionList;
    
	public Tema() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Tema(Long id_tema, String nombre, String contenido, Set<As_te> as_teList) {
		super();
		this.id_tema = id_tema;
		this.nombre = nombre;
		this.contenido = contenido;
		this.as_teList = as_teList;
	}

	public Long getId_tema() {
		return id_tema;
	}

	public void setId_tema(Long id_tema) {
		this.id_tema = id_tema;
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

	public Set<As_te> getAs_teList() {
		return as_teList;
	}

	public void setAs_teList(Set<As_te> as_teList) {
		this.as_teList = as_teList;
	}
    
    
}
