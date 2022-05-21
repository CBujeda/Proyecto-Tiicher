package com.example.demo.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.models.entity.Clase;
import com.example.demo.models.entity.Curso;
import com.example.demo.models.entity.Horario;
import com.example.demo.models.entity.Registro;
import com.example.demo.models.entity.Usuario;
import com.example.demo.models.service.IClaseService;
import com.example.demo.models.service.ICursoService;
import com.example.demo.models.service.IHorarioService;
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
	@Autowired
	private IHorarioService horarioService;
	
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
	
	
	@GetMapping("/index/user")
	public String goUser(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}		
		Usuario user = usuarioService.findByUsername(username);
		model.addAttribute("usuario", user);
		return "editUser";
	}
	
	
	@PostMapping("/index/user/update")
	public String userUpdate(Authentication auth, HttpSession session, Model model, @ModelAttribute Usuario user1) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}	
		
		Usuario user = usuarioService.findByUsername(username);
		user1.setId(user.getId());
		
			List<Usuario> usuarioList = usuarioService.findAll();
			for(int i = 0; i < usuarioList.size(); i++) {
				if(usuarioList.get(i).getUsername().equalsIgnoreCase(username)) {
					usuarioList.remove(i);
				}
			}
			for(int i = 0; i < usuarioList.size(); i++) {
				if(usuarioList.get(i).getUsername().equalsIgnoreCase(user1.getUsername())) {
					return "redirect:/private/index/user?error=true";
				}
			}

		if( user1.getUsername().length() < 3 ||
				user1.getNombre().length() < 3 ) {
			return "redirect:/private/index/user?error=true";
			
		}
		
		if(user1.getPassword().length() != 0 && user1.getPassword().length() < 3 ) {
			return "redirect:/private/index/user?errorPass=true";
		}
	
		if(!user1.getEmail().contains(".") && 
				 !user1.getEmail().contains("@") && 
				 !user1.getEmail().equalsIgnoreCase("") ){
			
			return "redirect:/private/index/user?emailNone=true";
		} 
		if(user1.getPassword().equalsIgnoreCase("")) {
			usuarioService.updateNoPass(user1);
		}else {
			usuarioService.updateWithPass(user1);
		}
		return "redirect:/private/index/user";
		
		
	}
	
	
	@PostMapping("/index/date/next")
	public String addDateNext(Authentication auth, HttpSession session, Model model,  @ModelAttribute Registro registro) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-mm-dd hh:mm:ss
			Date next = registro.getFecha_hora_inicio();
			Calendar c = Calendar.getInstance();
			c.setTime(next);
			c.add(Calendar.DATE, 1);
			next = c.getTime();
			String fecha = dateFormat.format(next);
			registro.setFecha_hora_inicio(next);
			
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
	
	@PostMapping("/index/date/previous")
	public String addDatePrevious(Authentication auth, HttpSession session, Model model,  @ModelAttribute Registro registro) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		
		try {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-mm-dd hh:mm:ss
			Date next = registro.getFecha_hora_inicio();
			Calendar c = Calendar.getInstance();
			c.setTime(next);
			c.add(Calendar.DATE, -1);
			next = c.getTime();
			String fecha = dateFormat.format(next);
			registro.setFecha_hora_inicio(next);
			
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
	
	
	@GetMapping("/index/list/horarios")
	public String editListHorario(Authentication auth, HttpSession session,Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		
		}
		
		try {
			List<Clase> listClases = claseService.findAll();
			List<Horario> listHorario = horarioService.findAll(); 
			model.addAttribute("clase",listClases);
			model.addAttribute("horario",listHorario);
		} catch (Exception e) {
			System.out.println(e.toString() + " Error en listas de curso");
		}
		
		return "listHorarios";
	}
	
	
	@GetMapping("/index/add/horario")
	public String editNewHorario(Authentication auth, HttpSession session,Model model) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		
		}
		
		Date d = new Date();
			 DateFormat dateFormat = new SimpleDateFormat("HH:mm");
			 Date date = new Date();
		  Horario timeNow = new Horario();
		  	timeNow.setHora_inicio(dateFormat.format(date));
		try {
			
			List<Clase> listClases = claseService.findAll();
			model.addAttribute("horario",new Horario());
			model.addAttribute("clase",listClases);
			model.addAttribute("timeNow",timeNow);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return "addHorario";
	}
	
	
	@PostMapping("/index/add/horario/new")
	public String addHorario(Authentication auth, HttpSession session, Model model,  @ModelAttribute Horario horario) {
		String username = auth.getName();
		if(session.getAttribute("usuario") == null) {
			Usuario usuario = usuarioService.findByUsername(username);
			usuario.setPassword(null);
			session.setAttribute("usuario", usuario);
		}
		
		try {
			//-- fecha junio
			DateFormat salidaformat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-mm-dd hh:mm:ss
			DateFormat year = new SimpleDateFormat("yyyy"); //yyyy
			Date now = new Date();
			String yearStr = year.format(now);
			Calendar cl = Calendar.getInstance();
			cl.setTime(now);
			cl.set(Integer.parseInt(yearStr), 5, 9);
			now = cl.getTime();
			String ultimoFecha = salidaformat.format(now);
			System.out.println("Ultima fecha" + ultimoFecha);
			
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); //yyyy-mm-dd hh:mm:ss
			Date next = new Date();
			Calendar c = Calendar.getInstance();
			
			DateFormat dateFormatoutput = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			
			while(true) {	
				String fecha = dateFormat.format(next);
				System.out.println(fecha);
				String output_fecha_inicio = fecha + " " + horario.getHora_inicio();
				String output_fecha_finalizacion = fecha +" " + horario.getHora_inicio();
				Calendar cfin = Calendar.getInstance();
				Date ofi = dateFormatoutput.parse(output_fecha_inicio);
				Date off = dateFormatoutput.parse(output_fecha_finalizacion);
				cfin.setTime(off);
				cfin.add(Calendar.HOUR, 1);
				off = cfin.getTime();
				System.out.println(ofi);
				System.out.println(off);
				
				Registro data = new Registro();
				data.setClase(horario.getClase());
				data.setFecha_hora_inicio(ofi);
				data.setFecha_hora_finalizacion(off);
				data.setHomework("");
				data.setAnotaciones("");
				data.setCancelado(0);
				if(data != null) {
					registroService.addRegistro(data);
					
				}
				
				
				if(fecha.equalsIgnoreCase(ultimoFecha)) {
					break;
				}
				c.setTime(next);
				c.add(Calendar.DATE, 1);
				next = c.getTime();
			}
			
			
			
			//horarioService.addHorario(horario);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return "redirect:/private/index/list/horarios";
	}
	
}
