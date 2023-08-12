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

import br.com.senai.contactapp.domain.contact.Contact;
import br.com.senai.contactapp.domain.contact.ContactService;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@Autowired
	private ContactService service;

	@GetMapping("/form/{id}")
	public ModelAndView form(@PathVariable String id) {
		ModelAndView mv = new ModelAndView("contacts/form");
		if ("novo".equals(id)) {
			mv.addObject("contact", Contact.builder().build());
			mv.addObject("message", "");
		} else {
			Contact contact = service.findById(UUID.fromString(id));
			mv.addObject("contact", contact != null ? 
												contact : 
												Contact.builder().build());
			mv.addObject("message", contact!= null ? "" : "Contato não encontrado" );
		}
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
		boolean result = service.save(contact);
		String message = result ? "Contato salvo com sucesso" : "Não foi possível salvar o registro";
		mv.addObject("message", message);
		mv.addObject("contact", contact);
		return mv;
	}

	@GetMapping()
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
		String message = result ? "Contato removido com sucesso" : "Não foi possível remover o registro";
		mv.addObject("contacts", service.findAll());
		mv.addObject("message", message);
		return mv;
	}
}
