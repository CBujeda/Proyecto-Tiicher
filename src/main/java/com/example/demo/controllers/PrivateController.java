package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
public class PrivateController {
	
	@Autowired
	private IUsuarioService usuarioService;
	@Autowired
	private IRegistroService registroService;
	@Autowired
	private ICursoService cursoService;
	@Autowired
	private IClaseService claseService;
	
	@GetMapping("/index")
	public String index(Authentication auth, HttpSession session,Model model) {
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
			List<Registro> listNow = registroService.findRegistroByHoy();
			model.addAttribute("registro",listNow);
			model.addAttribute("nowDate", datetimenow);
		
		
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "index";
	}
	
	
	@PostMapping("/index/add")
	public String addindex(Authentication auth, HttpSession session,Model model, Registro registro) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
			
		}
		if(registro != null) {
			registroService.addRegistro(registro);
			
		} else {
			return "redirect:/private/add?error=true";
		}
		
		return "redirect:/private/index";
	}
	

	@PostMapping("/index/add/curso")
	public String addCurso(Authentication auth, HttpSession session, Model model, @ModelAttribute Curso curso) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		try {
			if(curso != null) {
				if(!curso.getNombre_curso().equalsIgnoreCase("")) {
					cursoService.addCurso(curso);
				} else {
					return "redirect:/private/add/curso?error=true";
				}
			} else {
				return "redirect:/private/add/curso?error=true";
			}
		} catch(Exception e) {
				return "redirect:/private/add/curso?error=true";
		}
		
		return "redirect:/private/index";
	}
	
	@PostMapping("/index/add/clase")
	public String addClase(Authentication auth, HttpSession session, Model model,  @ModelAttribute Clase clase) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		try {
			if(clase != null) {
				if(!clase.getNombre_clase().equalsIgnoreCase("")) {
					claseService.addClase(clase);
				}else {
					return "redirect:/private/add/clase?error=true";
				}
			} else {
				return "redirect:/private/add/clase?error=true";
			}
		} catch (Exception e) {
			System.out.println(e.toString());
			return "redirect:/private/add/clase?error=true";
		}
		
		return "redirect:/private/index";
	}
	
	
	
	@PostMapping("/index/date")
	public String addDate(Authentication auth, HttpSession session, Model model,  @ModelAttribute Registro registro) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-mm-dd hh:mm:ss
			String fecha = dateFormat.format(registro.getFecha_hora_inicio());
			try {
				List<Registro> listNow = registroService.findRegistroByFecha(fecha);
				model.addAttribute("registro",listNow);
				model.addAttribute("nowDate", registro);
			} catch (Exception e) {
				System.out.println(e.toString());
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return "index";
	}
	
	
	

	@GetMapping("/index/delete/{id}")
	public String adddelete(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		try {
		registroService.deleteRegistrobyId(id);
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return "redirect:/private/index";
	}
	
	
	@GetMapping("/index/edit/{id}")
	public String addedit(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		
		try {
			Registro registro = registroService.getRegistroById(id);
			List<Clase> listClases = claseService.findAll();
			for(int i = 0; i < listClases.size();i++) {
				if(listClases.get(i).getId_clase() == registro.getClase().getId_clase()) {
					listClases.remove(i);
				}
			}
			
			model.addAttribute("clase",listClases);
			if(registro.getId_registro() == 0) {
				return "redirect:/private/index";
			} else {
				model.addAttribute("registro", registro);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return "edit";
	}
	
	@PostMapping("/index/edit/update")
	public String updateRegistro(Authentication auth, HttpSession session, Model model,  @ModelAttribute Registro registro) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		try {
			registroService.updateregistro(registro);
		}catch(Exception e) {
			System.out.println(e);
		}	
		return "redirect:/private/index";
	}
	
	
	@GetMapping("/index/edit/list/curso")
	public String editListCurso(Authentication auth, HttpSession session,Model model) {
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
		
		return "listCurso";
	}
	
	@GetMapping("/index/edit/curso/{id}")
	public String editCurso(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		Curso curso = cursoService.findById(id);
		model.addAttribute("curso",curso);
		return "editCurso";
	}
	
	
	@PostMapping("/index/edit/curso/update")
	public String editCursoUpdate(Authentication auth, HttpSession session, Model model, @ModelAttribute Curso curso) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		try {
			if(curso != null) {
				if(!curso.getNombre_curso().equalsIgnoreCase("")) {
					cursoService.updateById(curso);
				} else {
					return "redirect:/private/index/edit/list/curso?error=true";
				}
			} else {
				return "redirect:/private/index/edit/list/curso?error=true";
			}
		} catch(Exception e) {
				return "redirect:/private/index/edit/list/curso?error=true";
		}
		
		return "redirect:/private/index/edit/list/curso";
	}
	
	
	@GetMapping("/index/delete/curso/{id}")
	public String deleteCurso(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		try {
			cursoService.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
		return "redirect:/private/index/edit/list/curso";
	}
	
	
	@GetMapping("/index/edit/list/clase")
	public String editListClase(Authentication auth, HttpSession session,Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		
		}
		
		try {
			List<Clase> listNewClase = claseService.findAll();
			model.addAttribute("clase",listNewClase);
		} catch (Exception e) {
			System.out.println(e.toString() + " Error en listas de curso");
		}
		
		return "listClase";
	}
	
	
	@GetMapping("/index/edit/clase/{id}")
	public String editClase(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		Clase clase = claseService.findById(id);
		model.addAttribute("clase",clase);
		List<Curso> listNewCurso = cursoService.findAll();
		for(int i = 0; i < listNewCurso.size();i++ ) {
			if(listNewCurso.get(i).getId_curso() == clase.getCurso().getId_curso()) {
				listNewCurso.remove(i);
			}
		}
		
		model.addAttribute("curso",listNewCurso);
		return "editClase";
	}
	
	
	@PostMapping("/index/edit/clase/update")
	public String editClaseUpdate(Authentication auth, HttpSession session, Model model, @ModelAttribute Clase clase) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		
		try {
			if(clase != null) {
				if(!clase.getNombre_clase().equalsIgnoreCase("")) {
					claseService.updateById(clase);
				} else {
					return "redirect:/private/index/edit/list/clase?error=true";
				}
			} else {
				return "redirect:/private/index/edit/list/clase?error=true";
			}
		} catch(Exception e) {
				return "redirect:/private/index/edit/list/clase?error=true";
		}
		
		return "redirect:/private/index/edit/list/clase";
	}
	
	
	
	@GetMapping("/index/delete/clase/{id}")
	public String deleteClase(Authentication auth, HttpSession session, Model model,@PathVariable("id") long id) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		try {
			claseService.deleteById(id);
		}catch(Exception e) {
			System.out.println(e.toString());
		}
		
		
		return "redirect:/private/index/edit/list/clase";
	}
	
}
