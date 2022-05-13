package com.example.demo.models.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IClaseDAO;
import com.example.demo.models.entity.Clase;
import com.example.demo.models.service.IClaseService;
@Service
public class ClaseServiceImpl implements IClaseService {

	@Autowired
	private IClaseDAO claseDao;
	
	@Override
	public boolean addClase(Clase clase) {
		
		try {
			claseDao.save(clase);
			return true;
		}catch(Exception e) {
			System.out.println("Error al guardar clase");
			System.out.println(e.toString());
			return false;
		}

	}

	@Override
	public List<Clase> findAll() {
		return (List<Clase>) claseDao.findAll();
	}

	@Override
	public Clase findById(long id) {
		Optional<Clase> opt = claseDao.findById(id);
		
		if(opt.get() == null) {
			return new Clase((long) 0,null);
		} else {
			return opt.get();
	
		}
	}

	@Override
	public boolean updateById(Clase clase) {
		try{
			claseDao.updateById(clase.getNombre_clase(), 
								clase.getId_clase(), 
								clase.getCurso().getId_curso());
			return true;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
		
	}

	@Override
	public boolean deleteById(long id) {
		try{
			claseDao.deleteById(id);
			return true;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
	}
}
