package com.example.demo.models.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.models.dao.IUsuarioDAO;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IUsuarioService;


@Service
public class UsuarioServiceImpl implements IUsuarioService {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUsuarioDAO usuarioDao;
	

	
	@Override
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
	}

	@Override
	public Usuario registrar(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		return usuarioDao.save(u);
	}

	@Override
	public List<Usuario> findAll() {
		return usuarioDao.findAll();
	}

	@Override
	public boolean deletebyUser(Usuario u) {
		try {
			usuarioDao.delete(u);
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}

	@Override
	public boolean updateNoPass(Usuario u) {
		try {
			usuarioDao.updateByIdnoPass(	u.getId(), 
											u.getNombre(), 
											u.getApellido(), 
											u.getEmail());
			return true;
		}catch(Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}

	@Override
	public boolean updateWithPass(Usuario u) {
		u.setPassword(passwordEncoder.encode(u.getPassword()));
		try{
		usuarioDao.updateByIdWithPass(	u.getId(),
										u.getNombre(), 
										u.getApellido(), 
										u.getEmail(),
										u.getPassword());	
			return true;
		} catch (Exception e) {
			System.out.println(e.toString());
			return false;
		}
		
	}

}
