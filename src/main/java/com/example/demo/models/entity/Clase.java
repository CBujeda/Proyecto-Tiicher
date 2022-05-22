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
@Table(name = "clase")
public class Clase implements Serializable {

	private static final long serialVersionUID = -8584418286231310605L;
	
	@Id
	@GeneratedValue
	private long id_clase;
	private String nombre_clase;
	
	@OneToMany(mappedBy = "clase",orphanRemoval = true)
	private Set<Registro> listRegistro;
	
	@OneToMany(mappedBy = "clase",orphanRemoval = true)
	private Set<Horario> listHorario;
	
    @ManyToOne
    @JoinColumn(name = "id_curso") // creamos la columna de tipo objeto con joinColumn
    private Curso curso;
	
	public Clase() {
		super();
	}
	public Clase(long id_clase, String nombre_clase) {
		super();
		this.id_clase = id_clase;
		this.nombre_clase = nombre_clase;
	}
	public long getId_clase() {
		return id_clase;
	}
	public void setId_clase(long id_clase) {
		this.id_clase = id_clase;
	}
	public String getNombre_clase() {
		return nombre_clase;
	}
	public void setNombre_clase(String nombre_clase) {
		this.nombre_clase = nombre_clase;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
}
