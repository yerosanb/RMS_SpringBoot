package com.example.demo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security..GrantedAuthority;
import org.springframework.security..userdetails.UserDetails;
import org.springframework.security..userdetails.UserDetailsService;
import org.springframework.security..userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.MapperAuth;
import com.example.demo.model.Roles;
import com.example.demo.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private MapperAuth mapper;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("iiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiyuyyyyyyyyyyyyyyyyy: " + email);
		User user = mapper.findByUserName(email)
				.orElseThrow(() -> new UsernameNotFoundException("No user found " + email));
		return new org.springframework.security..userdetails.User(user.getEmail(), user.getPassword(), true,
				true, true, true, getAuthorities(user.getRoles()));
	}

	private Collection<? extends GrantedAuthority> getAuthorities(Collection<Roles> roles) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);

		for (Roles role : roles) {

			authList.add(new GrantedAuthority() {
				@Override
				public String getAuthority() {
					// TODO Auto-generated method stub
					return role.getName();
				}
			});
		}
		return authList;
	}

}
