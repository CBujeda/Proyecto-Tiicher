package com.example.demo.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.entity.Usuario;

@Repository
public interface IUsuarioDAO extends JpaRepository<Usuario,Long> {
	public Usuario findByUsername(String username);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value=""
			+ "update usuarios u "
			+ "set u.nombre = ?2 , "
			+ " u.apellido = ?3 , "
			+ " u.email = ?4 , "
			+ " u.password = ?5 "
			+ "where u.id = ?1 ")
	public void updateByIdWithPass(Long id, String nombre, String apellido, String email, String password);
	
	@Modifying
	@Transactional
	@Query(nativeQuery=true, value=""
			+ "update usuarios u "
			+ "set u.nombre = ?2 , "
			+ " u.apellido = ?3 , "
			+ " u.email = ?4 "
			+ "where u.id = ?1 ")
	public void updateByIdnoPass(Long id, String nombre, String apellido, String email);
}
