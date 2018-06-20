package com.mwt.security.authorization;

import org.springframework.security.core.GrantedAuthority;

public class ApiGrantedAuthority implements GrantedAuthority {
	
	private static final long serialVersionUID = 3191466342692912291L;
	
	private String authority;
	
	public ApiGrantedAuthority(String role) {
		this.authority = role;
	}

	@Override
	public String getAuthority() {
		return this.authority;
	}

}
