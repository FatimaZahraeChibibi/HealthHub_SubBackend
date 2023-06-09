package com.healthHub.healthHub.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.healthHub.healthHub.classes.ErrorResponse;
import com.healthHub.healthHub.classes.loginRequer;
import com.healthHub.healthHub.classes.personneLogin;
import com.healthHub.healthHub.model.Personne;
import com.healthHub.healthHub.repository.PersonneRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class loginController {

	private final PersonneRepository personneRepository;

	@Autowired
	public loginController(PersonneRepository personneRepository) {
		this.personneRepository = personneRepository;
	}

	@PostMapping("/login")
	public ResponseEntity<?> getPersonneBylogin(@RequestBody loginRequer login) {
		//Get the password of the person (crypted and saved in DB)
		
		//Call the same class for encryption
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		Optional<Personne>  personne = personneRepository.findByemail(login.getEmail());
		//There's no decrypting method in this class => use matching passwords (raw, crypted)
		
		if (personne.isPresent() && bcrypt.matches(login.getPassword(), personne.get().getPassword())) 
		{
			personneLogin p=new personneLogin(personne.get().getlastName(),personne.get().getfirstName(),personne.get().getBirthDate(),personne.get().getTelephone(),personne.get().getEmail(),personne.get().getHub().getHubId(),personne.get().getRole());
			return new ResponseEntity<>(p, HttpStatus.OK);
		} else {
			String errorMessage = "Incorrect Email or Password";
	        ErrorResponse errorResponse = new ErrorResponse(errorMessage);
	        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/changepassword")
	public ResponseEntity<?> changePassword(@RequestBody loginRequer login) {
		Optional<Personne> personne = personneRepository.findByemail(login.getEmail());
		if (personne.isPresent()) {
			BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
			String encryPwd= bcrypt.encode(login.getPassword());
			Personne existingPersonne = personne.get();
			existingPersonne.setPassword(encryPwd);
			Personne savedPersonne = personneRepository.save(existingPersonne);
			return new ResponseEntity<>(savedPersonne, HttpStatus.OK);
		} else {
			String errorMessage = "Email not found";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage);
			return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
		}
	}
}