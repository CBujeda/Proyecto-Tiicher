package com.example.demo.models.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IHorarioDAO;
import com.example.demo.models.entity.Horario;
import com.example.demo.models.service.IHorarioService;


@Service
public class HorarioServiceImpl implements IHorarioService {
	
	@Autowired
	private IHorarioDAO horarioDAO;
	
	@Override
	public boolean addHorario(Horario h) {
		try {
			horarioDAO.save(h);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Horario> findAll() {
		return (List<Horario>) horarioDAO.findAll();
	}

	@Override
	public boolean deleteById(long id) {
		try {
			horarioDAO.deleteById(id);
			return true;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	

}
