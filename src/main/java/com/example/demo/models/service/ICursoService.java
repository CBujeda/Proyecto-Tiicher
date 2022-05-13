package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Curso;


public interface ICursoService {
	
	public boolean addCurso(Curso curso);
	
	public Curso findById(long id);
	public List<Curso> findAll();
	
	public boolean deleteById(long id);
	
	public boolean updateById(Curso curso);
}
