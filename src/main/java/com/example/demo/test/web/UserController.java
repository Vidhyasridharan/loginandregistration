package com.example.demo.test.web;


import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.test.model.User;
import com.example.demo.test.repository.Userrepository;
import com.example.demo.test.services.Userservices;
import com.example.demo.test.web.dto.UserDto;

@Controller

public class UserController {
private Userservices userservices;
	public UserController(Userservices userservices) {
	super();
	this.userservices = userservices;
	}
	@Autowired
    private Userrepository userrepository;

	@GetMapping("/registration")
	public String registration(Model model) {
		UserDto userDto=new UserDto();
		model.addAttribute("user", userDto);
		return "registration_page";
	}
	@PostMapping("/registration")
	public String  saveregistration(@ModelAttribute("user") UserDto userdto) {
	     userservices.saveUser(userdto);
         return "redirect:/registration?success";
	}
	 @GetMapping("/login") 
	 public String login() { 
		 return "login"; 
		 }
	 
		
	 @GetMapping("/") 
	 public String home() { return "index"; }
	 
		
		 @GetMapping("/homepage") 
		 public String test( Model model,@ModelAttribute("user") UserDto userDto) { 
			 Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			    String email = loggedInUser.getName(); 

			     User user = userrepository.findByEmail(email);
			    String firstname = user.getFirstName();
			     model.addAttribute("firstName", firstname);
			    
			 return "homepage"; 
		 
		 }
	
}
