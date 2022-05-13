package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "asignatura")
public class Asignatura implements Serializable{

	private static final long serialVersionUID = -7122693872308181827L;
	
	@Id
	@GeneratedValue
	private Long id_asignatura;
	private String nombre;
	private String contenido;
	
    @OneToMany(mappedBy = "asignatura")   
    private Set<Cu_as> cu_asList;
    
    @OneToMany(mappedBy = "asignatura")   
    private Set<Horario> horarioList;

    @OneToMany(mappedBy = "asignatura")   
    private Set<As_te> as_teList;
    
	public Asignatura() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Asignatura(Long id_asignatura, String nombre, String contenido, Set<Cu_as> cu_asList) {
		super();
		this.id_asignatura = id_asignatura;
		this.nombre = nombre;
		this.contenido = contenido;
		this.cu_asList = cu_asList;
	}

	public Long getId_asignatura() {
		return id_asignatura;
	}

	public void setId_asignatura(Long id_asignatura) {
		this.id_asignatura = id_asignatura;
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

	public Set<Cu_as> getCu_asList() {
		return cu_asList;
	}

	public void setCu_asList(Set<Cu_as> cu_asList) {
		this.cu_asList = cu_asList;
	} 
    
    
}
