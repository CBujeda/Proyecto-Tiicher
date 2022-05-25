package com.example.demo.models.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IRegistroDAO;
import com.example.demo.models.entity.Registro;
import com.example.demo.models.service.IRegistroService;

@Service
public class RegistroServiceImpl implements IRegistroService {
	
	@Autowired
	private IRegistroDAO registroDao;

	@Override
	public List<Registro> findRegistroByHoy() {
		return registroDao.findbyhoy();
	}

	/**
	 * Pre:
	 * Post: Metodo el cual añade un registro
	 */
	@Override
	public boolean addRegistro(Registro registro) {
		try {
			registroDao.save(registro);
			return true;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve registros por una fecha
	 */
	@Override
	public List<Registro> findRegistroByFecha(String fecha) {
		return registroDao.findbyFecha(fecha);
	}

	/**
	 * Pre:
	 * Post: Metodo el cual elimina un registro  por id
	 */
	@Override
	public boolean deleteRegistrobyId(Long id) {
		try {
			registroDao.deleteById(id);
			return true;
		}catch(Exception e) {
			return false;
		}
		
		
	}

	/**
	 * Pre:
	 * Post: Metodo el cual devuelve un registro por id
	 */
	@Override
	public Registro getRegistroById(Long id) {
		Optional<Registro> opt = registroDao.findById(id);
		if(opt.get() == null) {
			return new Registro(0, null, null, 0,null,null);
		} else {
			return opt.get();
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual actualiza un registro
	 */
	@Override
	public boolean updateregistro(Registro registro) {
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			String fecha_inicio = dateFormat.format(registro.getFecha_hora_inicio());;
			String fecha_fin = dateFormat.format(registro.getFecha_hora_finalizacion());;
			registroDao.updateById( registro.getId_registro(), 
									registro.getAnotaciones(), 
									fecha_fin, 
									fecha_inicio, 
									registro.getHomework(), 
									registro.getClase().getId_clase());
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return false;
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve todos los registros
	 */
	@Override
	public List<Registro> findAll() {
		return (List<Registro>) registroDao.findAll();
	}
	
}
