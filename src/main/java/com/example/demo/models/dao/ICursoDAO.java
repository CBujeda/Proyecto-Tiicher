package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Curso;
@Repository
public interface ICursoDAO extends JpaRepository<Curso,Long> {
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value=""
			+ "update curso cu "
			+ "set cu.nombre_curso = ?1 "
			+ "where cu.id_curso = ?2 ")
	public void updateById(String nombre, long id);

}
