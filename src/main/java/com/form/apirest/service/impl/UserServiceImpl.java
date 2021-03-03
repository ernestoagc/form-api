package com.form.apirest.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.form.apirest.repository.*;
@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService {

	@Autowired 
	UserRepository userDao;

	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.form.apirest.model.User user=null;
		
		user= userDao.findByUserCode(username);
		if(user==null) {
			//logger.error("User does not exits " + usuario.getCodigo());
			throw new UsernameNotFoundException("User does not exits " + user.getUserCode());
		}
		
		List<GrantedAuthority> authorities = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getCode()))
				.peek(aut ->logger.info("Rol: " + aut.getAuthority()))
				.collect(Collectors.toList());
		
		logger.info("authenticated user: " + username);
		
		return new User(user.getUserCode(), user.getPassword(), true, 
				true, true, true, authorities);
	}
	
	

}
