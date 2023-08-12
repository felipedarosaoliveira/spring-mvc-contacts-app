package br.com.senai.contactapp.domain.contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ContactService {
	

	private List<Contact> contacts = new ArrayList<Contact>();
	
	public boolean createContact(Contact contact) {
		if(contact == null) {
			return false;
		}
		contact.setId(UUID.randomUUID());
		contacts.add(contact);
		return true;
	}
	
	public boolean update(Contact contact) {
		if(contact == null) {
			return false;
		}
		if(findById(contact.getId()) == null) {
			return false;
		}
		contacts = contacts
				.stream()
				.map((current)->{
					if(current.getId().equals(contact.getId())) {
						return contact;
					}
					return current;
				})
				.toList();
		
		return true;
	}
	
	public boolean save(Contact contact) {
		if(contact.getId() != null) {
			return update(contact);
		}
		return createContact(contact);
	}
	
	public List<Contact> findAll(){
		return contacts;
	}
	
	public Contact findById(final UUID id) {
		Contact contact = contacts
				.stream()
				.filter((current)-> current.getId().equals(id))
				.findFirst()
				.orElse(null);
		return contact;
	}

	public boolean removeContact(final UUID id) {
		int total = contacts.size();
		contacts = contacts
				.stream()
				.filter((current)-> !current.getId().equals(id))
				.toList();
		
		return total > contacts.size();
	}

}
