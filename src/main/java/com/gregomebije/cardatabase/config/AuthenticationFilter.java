package com.gregomebije.cardatabase.config;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.gregomebije.cardatabase.service.JwtService;
import java.util.Collection;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;

	public AuthenticationFilter(JwtService jwtService) {
		this.jwtService = jwtService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, java.io.IOException {
		
		String jws = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (jws != null) {
			String user = jwtService.getAuthUser(request);
			// 👈 FIX: Extract the roles claim directly out of the token payload
			Collection<? extends GrantedAuthority> authorities = jwtService.getAuthRoles(request);
			
			if (user != null) {
				// 👈 FIX: Map the reconstructed authorities back into the context
				Authentication authentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		filterChain.doFilter(request, response);
	}
}
