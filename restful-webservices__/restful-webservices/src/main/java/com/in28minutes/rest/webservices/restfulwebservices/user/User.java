package com.in28minutes.rest.webservices.restfulwebservices.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Past;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;

import  jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity(name = "user_deatils")
public class User {
	private static final Logger LOGGER = LogManager.getLogger(User.class);

	static {
		LOGGER.info("Initializing User class");
	}

     @Id
	 @GeneratedValue
	private Integer id;
	@Size(min = 2 , message = "Name should have atleast 2 characters")
	@JsonProperty("User_Name")
	private String name;
	@Past(message = "Birth date should be in the past")
	@JsonProperty("birth_date")
	private LocalDate birthDate;
	public User(Integer id, String name, LocalDate birthDate) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}
}
