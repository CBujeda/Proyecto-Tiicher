package com.example.demo.models.service;

import java.util.List;

import com.example.demo.models.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
	public Usuario registrar(Usuario u);
	public List<Usuario> findAll();
	public boolean deletebyUser(Usuario u);
	public boolean updateNoPass(Usuario u);
	public boolean updateWithPass(Usuario u);
	
}
