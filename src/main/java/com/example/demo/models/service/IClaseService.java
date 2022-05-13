package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Clase;

public interface IClaseService {
	
	public boolean addClase(Clase clase);
	
	public List<Clase> findAll();
	
	public Clase findById(long id);
	
	public boolean deleteById(long id);
	
	public boolean updateById(Clase clase);
}
