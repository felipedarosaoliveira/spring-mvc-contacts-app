package br.com.senai.contactapp.infra;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.senai.contactapp.domain.contact.Contact;

@Repository
public class ContactRepository {
	
	@Autowired
	private JdbcTemplate db;
	
	public List<Contact> findAll(){
		String sql = "select * from contact";
		return db.query(sql,(resultset,index)-> {
			return toContact(resultset);
		});
	}
	
	private Contact toContact(ResultSet resultset) throws SQLException {
		return Contact.builder()
				.id(UUID.fromString(resultset.getString("id")))
				.name(resultset.getString("name"))
				.email(resultset.getString("email"))
				.phone(resultset.getString("phone"))
				.build();
	}
	
	public Contact insert(Contact contact) {
		UUID id = UUID.randomUUID();
		String sql = "insert into contact (id, name, email, phone) values (?,?,?,?)";
		int result = db.update(sql, 
				          id,contact.getName(), contact.getEmail(),contact.getPhone());
		if(result == 1) {
			contact.setId(id);
		}
		return contact;
	}
	
	
	
	
	
	public Contact findById(UUID id) {
		String sql = "select * from contact where id = ?";
		return db.queryForObject(sql, (resultset,index)->{
			return toContact(resultset);
		},id);
	}
	
	
	
	public Contact update(Contact contact) {
		UUID id = UUID.randomUUID();
		String sql = "update  contact set name = ?, email = ? , phone = ? where id = ?";
		int result = db.update(sql, contact.getName(),contact.getEmail(),contact.getPhone(),contact.getId());
		if(result == 1) {
			contact.setId(id);
		}
		return contact;
	}
	
	

}
