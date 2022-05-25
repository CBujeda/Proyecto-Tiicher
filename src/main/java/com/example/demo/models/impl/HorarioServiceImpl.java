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
	
	/**
	 * Pre:
	 * Post: Metodo el cual añade un horario a la BBDD
	 */
	@Override
	public boolean addHorario(Horario h) {
		try {
			horarioDAO.save(h);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Pre:
	 * Post: Metodo el cual devuelve todos los horarios
	 */
	@Override
	public List<Horario> findAll() {
		return (List<Horario>) horarioDAO.findAll();
	}

	/**
	 * Pre:
	 * Post: Metodo el cual elimina un horario por id
	 */
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
