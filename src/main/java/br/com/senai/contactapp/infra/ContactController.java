package br.com.senai.contactapp.infra;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/contacts")
public class ContactController {

	@GetMapping()
	public String show() {
		return "contacts/list";
	}
	
	@GetMapping("/form/{id}")
	public String form(@PathVariable String id) {
		return "contacts/form";
	}
}
