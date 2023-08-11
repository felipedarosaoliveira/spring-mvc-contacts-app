package br.com.senai.contactapp.infra;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.senai.contactapp.domain.Contact;
import br.com.senai.contactapp.domain.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	private ContactService service;

	@GetMapping()
	public String show() {
		return "contacts/list";
	}
	
	@GetMapping("/form/{id}")
	public ModelAndView form(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("contacts/form");
		mv.addObject("message", "");
		mv.addObject("contact", Contact.builder().build());
		return mv;
	}
	
	@GetMapping("/form")
	public ModelAndView showNewFormContact() {
		ModelAndView mv = new ModelAndView("contacts/form");
		mv.addObject("message", "");
		mv.addObject("contact", Contact.builder().build());
		return mv;
	}
	
	@PostMapping("/saveContact")
	public ModelAndView save(@ModelAttribute Contact contact) {
		ModelAndView mv = new ModelAndView("contacts/form");
		boolean result = service.createContact(contact);
		String message = result ? 
				"Contato criado com sucesso" :
				"Não foi possível criar o registro";
		mv.addObject("message", message);
		mv.addObject("contact", contact);
		return mv;
	}
	
	@GetMapping("/list")
	public ModelAndView listAll() {
		ModelAndView mv = new ModelAndView("contacts/list");
		mv.addObject("contacts", service.findAll());
		mv.addObject("message", "");
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView remove(@PathVariable UUID id) {
		ModelAndView mv = new ModelAndView("contacts/list");
		boolean result = service.removeContact(id);
		String message = result ? 
				"Contato removido com sucesso" :
				"Não foi possível remover o registro";
		mv.addObject("contacts", service.findAll());
		mv.addObject("message", message);
		return mv;
	}
}
