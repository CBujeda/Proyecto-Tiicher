package com.example.demo.models.impl;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.ICursoDAO;
import com.example.demo.models.entity.Curso;
import com.example.demo.models.service.ICursoService;
@Service
public class CursoServiceImpl implements ICursoService {
	
	@Autowired
	private ICursoDAO cursoDao;
	
	/**
	 * Pre:
	 * Post: Metodo el cual alade un curso
	 */
	@Override
	public boolean addCurso(Curso curso) {
		// TODO Auto-generated method stub
		try {
			cursoDao.save(curso);
			return true;
		}catch (Exception e) {
			System.out.println("Error al guardar curso");
			System.out.println(e.toString());
			return false;	
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve todos los cursos
	 */
	@Override
	public List<Curso> findAll() {
		return (List<Curso>) cursoDao.findAll();
	}

	/**
	 * Pre:
	 * Post: Metodo el cual devuelve un curso por id
	 */
	@Override
	public Curso findById(long id) {
		Optional<Curso> opt = cursoDao.findById(id);
		if(opt.get() == null) {
			return new Curso((long) 0,null);
		}else {
			return opt.get();
		}
	}
	
	/**
	 * Pre:
	 * Post: Metodo elc ual actualiza un curso por id
	 */
	@Override
	public boolean updateById(Curso curso) {
		try {
			cursoDao.updateById(curso.getNombre_curso(), curso.getId_curso());
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual elimina un curo por id
	 */
	@Override
	public boolean deleteById(long id) {
		try {
			cursoDao.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
