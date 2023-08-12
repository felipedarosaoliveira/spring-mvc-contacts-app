package br.com.senai.contactapp.domain.user;

import java.util.UUID;

import br.com.senai.contactapp.domain.contact.Contact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {
	
	@EqualsAndHashCode.Include
	private UUID id;
	
	private String name;
	private String email;

}
