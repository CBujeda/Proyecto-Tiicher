package com.example.demo.controllers;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.entity.Clase;
import com.example.demo.models.entity.Curso;
import com.example.demo.models.entity.Registro;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IClaseService;
import com.example.demo.models.service.ICursoService;
import com.example.demo.models.service.IRegistroService;
import com.example.demo.models.service.IUsuarioService;

@Controller
@RequestMapping("/private")
public class AddController {

	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IRegistroService registroService;
	@Autowired
	private ICursoService cursoService;
	@Autowired
	private IClaseService claseService;
	
	@GetMapping("/add")
	public String add(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			
		}
		Date timeNow = new Date();
		Registro datetimenow = new Registro();
		datetimenow.setFecha_hora_inicio(timeNow);
		
		try {
			List<Clase> listClases = claseService.findAll();
			model.addAttribute("clase",listClases);
			model.addAttribute("nowDate", datetimenow);
		} catch (Exception e) {
			System.out.println(e.toString() + "Error en las listas de clase");
		}
		
		return "new";
	}
	
	@GetMapping("/add/curso")
	public String addCurso(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			
		}
		return "newCurso";
	}
	
	@GetMapping("/add/clase")
	public String addClase(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			
		}
		
		try {
			List<Curso> listNewCurso = cursoService.findAll();
			model.addAttribute("curso",listNewCurso);
		} catch (Exception e) {
			System.out.println(e.toString() + " Error en listas de curso");
		}
		return "newClase";
	}
}
