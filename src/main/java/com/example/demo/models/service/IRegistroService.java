package com.example.demo.models.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.entity.Registro;

public interface IRegistroService {
	
	public List<Registro> findRegistroByHoy();
	
	public boolean addRegistro(Registro registro);
	
	public boolean updateregistro(Registro registro);
	
	public List<Registro> findRegistroByFecha(String fecha);
	
	public boolean deleteRegistrobyId(Long id);
	
	public Registro getRegistroById(Long id);
	
}
