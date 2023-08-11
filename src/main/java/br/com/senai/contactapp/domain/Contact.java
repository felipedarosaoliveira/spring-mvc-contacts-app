package br.com.senai.contactapp.domain;

import java.util.UUID;

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
public class Contact {
	
	@EqualsAndHashCode.Include
	private UUID id;
	private String name;
	private String email;
	private String phone;
	
	

}
