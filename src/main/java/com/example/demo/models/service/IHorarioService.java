package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Horario;

public interface IHorarioService  {

	public boolean addHorario(Horario h);
	
	public List<Horario> findAll();
	
}
