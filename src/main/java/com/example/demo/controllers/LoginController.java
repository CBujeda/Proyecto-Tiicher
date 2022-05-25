package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IUsuarioService;

@Controller
public class LoginController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve la pantalla de login
	 */
	@GetMapping("/auth/login")
	public String login(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "login";
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve la pantalla de registro
	 */
	@GetMapping("/auth/registro")
	public String registroForm(Model model) {
		model.addAttribute("usuario", new Usuario());
		return "registro";
	}
	
	/**
	 * Pre:
	 * Post: Metodo el cual devuelve un registro a la BBDD
	 */
	@PostMapping("/auth/registro")
	public String registro(@Validated @ModelAttribute Usuario usuario, BindingResult result, Model model) {
		Usuario user;

		try {
			user = usuarioService.findByUsername(usuario.getUsername());
			
		}catch(Exception e) {
			return "redirect:/auth/registro?error=true";
		}
		if(result.hasErrors()) {
			return "redirect:/auth/registro";
		}else {
			if( usuario.getUsername().length() < 3 ||
				usuario.getNombre().length() < 3 ||
				usuario.getPassword().length() < 3 || user != null ) {
				return "redirect:/auth/registro?error=true";
				
			}
			if(!usuario.getEmail().contains(".") && 
					 !usuario.getEmail().contains("@") && 
					 !usuario.getEmail().equalsIgnoreCase("") ){
				
				return "redirect:/auth/registro?emailNone=true";
			} else {
				model.addAttribute("usuario", usuarioService.registrar(usuario));
				return "redirect:/auth/login";
			}
		}
	}
	
}



