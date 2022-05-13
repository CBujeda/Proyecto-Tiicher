package com.example.demo.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "horario")
public class Horario implements Serializable {

	private static final long serialVersionUID = 7349326900222442193L;

	@Id
	@GeneratedValue
	private long id_horario;
	
	private String dia_semana;

    private String hora_inicio;
    private String hora_fin;

    @ManyToOne
    @JoinColumn(name = "id_clase") // creamos la columna de tipo objeto con joinColumn
    private Clase clase;

    @ManyToOne
    @JoinColumn(name = "id_asignatura") // creamos la columna de tipo objeto con joinColumn
    private Asignatura asignatura;
    
	public Horario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Horario(long id_horario, String dia_semana, String hora_inicio, String hora_fin, Clase clase) {
		super();
		this.id_horario = id_horario;
		this.dia_semana = dia_semana;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
		this.clase = clase;
	}

	public long getId_horario() {
		return id_horario;
	}

	public void setId_horario(long id_horario) {
		this.id_horario = id_horario;
	}

	public String getDia_semana() {
		return dia_semana;
	}

	public void setDia_semana(String dia_semana) {
		this.dia_semana = dia_semana;
	}

	public String getHora_inicio() {
		return hora_inicio;
	}

	public void setHora_inicio(String hora_inicio) {
		this.hora_inicio = hora_inicio;
	}

	public String getHora_fin() {
		return hora_fin;
	}

	public void setHora_fin(String hora_fin) {
		this.hora_fin = hora_fin;
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}
	
	
}
