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
	
	public Contact findById(UUID id) {
		String sql = "select * from contact where id = ?";
		return db.queryForObject(sql, (resultset,index)->{
			return toContact(resultset);
		},id);
	}
	
	private Contact toContact(ResultSet resultset) throws SQLException {
		return Contact.builder()
				.id(UUID.fromString(resultset.getString("id")))
				.name(resultset.getString("name"))
				.email(resultset.getString("email"))
				.phone(resultset.getString("phone"))
				.build();
	}

}
