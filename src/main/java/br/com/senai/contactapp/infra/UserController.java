package br.com.senai.contactapp.infra;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senai.contactapp.domain.user.User;
import br.com.senai.contactapp.domain.user.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;

	@GetMapping("/form/{id}")
	public ModelAndView form(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("users/form");
		if ("novo".equals(id)) {
			mv.addObject("user", User.builder().build());
			mv.addObject("message", "");
		} else {
			User user= service.findById(UUID.fromString(id));
			mv.addObject("user", user!= null ? 
												user : 
												User.builder().build());
			mv.addObject("message", user!= null ? "" : "Usuário não encontrado" );
		}
		return mv;
	}

	@GetMapping("/form")
	public ModelAndView showNewFormUser() {
		ModelAndView mv = new ModelAndView("users/form");
		mv.addObject("message", "");
		mv.addObject("user", User.builder().build());
		return mv;
	}

	@PostMapping("/saveUser")
	public ModelAndView save(@ModelAttribute User user) {
		ModelAndView mv = new ModelAndView("users/form");
		boolean result = service.save(user);
		String message = result ? "Usuário salvo com sucesso" : "Não foi possível salvar o registro";
		mv.addObject("message", message);
		mv.addObject("user", user);
		return mv;
	}

	@GetMapping()
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView("users/list");
		mv.addObject("users", service.findAll());
		mv.addObject("message", "");
		return mv;
	}

	@GetMapping("/delete/{id}")
	public ModelAndView remove(@PathVariable UUID id) {
		ModelAndView mv = new ModelAndView("users/list");
		boolean result = service.removeUser(id);
		String message = result ? "Usuário removido com sucesso" : "Não foi possível remover o registro";
		mv.addObject("users", service.findAll());
		mv.addObject("message", message);
		return mv;
	}

}
