package com.in28minutes.learning.jpa.jpain10steps.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	private String name;
	
	private String role;

	public User(String name, String role) {
		super();
		this.name = name;
		this.role = role;
	}
	
	
}
