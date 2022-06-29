package com.example.demo.test.servicesImpl;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.test.model.Role;
import com.example.demo.test.model.User;
import com.example.demo.test.repository.Userrepository;
import com.example.demo.test.services.Userservices;
import com.example.demo.test.web.dto.UserDto;
@Transactional
@Service

	public class UserservicesImpl implements Userservices{
	
		private Userrepository userRepository;
		@Autowired
		private BCryptPasswordEncoder passwordEncoder;
		public UserservicesImpl(Userrepository userRepository) {
			super();
			this.userRepository = userRepository;
		}
		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user=userRepository.findByEmail(username);
			if(user== null) {
				throw new UsernameNotFoundException("Invalid username or password.");	
			}
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
		}
		private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
			// TODO Auto-generated method stub
			return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		}
		
		public User saveUser(UserDto userdto) {
			User user =new User(userdto.getFirstName(), 
					userdto.getLastName(), userdto.getEmail(),
					passwordEncoder.encode(userdto.getPassword()), Arrays.asList(new Role("ROLE_USER")));
			return userRepository.save(user);
		}

		
	}

