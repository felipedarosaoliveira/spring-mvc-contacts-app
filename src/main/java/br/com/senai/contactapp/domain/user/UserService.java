package br.com.senai.contactapp.domain.user;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.senai.contactapp.domain.contact.Contact;

@Service
public class UserService {

	private List<User> users = new ArrayList<User>();
	
	public boolean createUser(User user) {
		if(user == null) {
			return false;
		}
		user.setId(UUID.randomUUID());
		users.add(user);
		return true;
	}
	
	public boolean update(User user) {
		if(user == null) {
			return false;
		}
		if(findById(user.getId()) == null) {
			return false;
		}
		users = users
				.stream()
				.map((current)->{
					if(current.getId().equals(user.getId())) {
						return user;
					}
					return current;
				})
				.toList();
		
		return true;
	}
	
	public boolean save(User user) {
		if(user.getId() != null) {
			return update(user);
		}
		return createUser(user);
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User findById(final UUID id) {
		User user= users
				.stream()
				.filter((current)-> current.getId().equals(id))
				.findFirst()
				.orElse(null);
		return user;
	}

	public boolean removeUser(final UUID id) {
		int total = users.size();
		users = users
				.stream()
				.filter((current)-> !current.getId().equals(id))
				.toList();
		
		return total > users.size();
	}
	
}
