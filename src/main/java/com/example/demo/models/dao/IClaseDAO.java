package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Clase;
@Repository
public interface IClaseDAO extends JpaRepository<Clase,Long> {

	@Modifying
	@Transactional
	@Query(nativeQuery=true, value=""
			+ "update clase cl "
			+ "set cl.nombre_clase = ?1 , "
			+ "cl.id_curso = ?3 "
			+ "where cl.id_clase = ?2 ")
	public void updateById(String nombre, long id, long id_curso);
	
}
