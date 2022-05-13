package com.example.demo.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "registro")
public class Registro implements Serializable {

	private static final long serialVersionUID = 695705072870964904L;

	@Id
	@GeneratedValue
	private long id_registro;
	
    @ManyToOne
    @JoinColumn(name = "id_clase") // creamos la columna de tipo objeto con joinColumn
    private Clase clase;
    @OneToMany(mappedBy = "registro")   
    private Set<Re_se> re_seList;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+1")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha_hora_inicio;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+1")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private Date fecha_hora_finalizacion;
	private int cancelado;
	private String anotaciones;
	private String homework;
	public Registro() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Registro(long id_registro, Date fecha_hora_inicio, Date fecha_hora_finalizacion, int cancelado,
			String anotaciones, String homework) {
		super();
		this.id_registro = id_registro;
		this.fecha_hora_inicio = fecha_hora_inicio;
		this.fecha_hora_finalizacion = fecha_hora_finalizacion;
		this.cancelado = cancelado;
		this.anotaciones = anotaciones;
		this.homework = homework;
	}
	public long getId_registro() {
		return id_registro;
	}
	public void setId_registro(long id_registro) {
		this.id_registro = id_registro;
	}
	public Date getFecha_hora_inicio() {
		return fecha_hora_inicio;
	}
	public void setFecha_hora_inicio(Date fecha_hora_inicio) {
		this.fecha_hora_inicio = fecha_hora_inicio;
	}
	public Date getFecha_hora_finalizacion() {
		return fecha_hora_finalizacion;
	}
	public void setFecha_hora_finalizacion(Date fecha_hora_finalizacion) {
		this.fecha_hora_finalizacion = fecha_hora_finalizacion;
	}
	public int getCancelado() {
		return cancelado;
	}
	public void setCancelado(int cancelado) {
		this.cancelado = cancelado;
	}
	public String getAnotaciones() {
		return anotaciones;
	}
	public void setAnotaciones(String anotaciones) {
		this.anotaciones = anotaciones;
	}
	public String getHomework() {
		return homework;
	}
	public void setHomework(String homework) {
		this.homework = homework;
	}
	public Clase getClase() {
		return clase;
	}
	public void setClase(Clase clase) {
		this.clase = clase;
	}
	public Set<Re_se> getRe_seList() {
		return re_seList;
	}
	public void setRe_seList(Set<Re_se> re_seList) {
		this.re_seList = re_seList;
	}


	
	
	
}
