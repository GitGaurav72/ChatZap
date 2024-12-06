package com.novaedge.chatzap.api.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.novaedge.chatzap.api.services.CustomUserDetailsService;
import com.novaedge.chatzap.api.util.JwtUtil;
import lombok.extern.apachecommons.CommonsLog;

@Component
public class JwtFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private CustomUserDetailsService service;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		
		String authorizationHeade = request.getHeader("Authorization"); 
		String token = null;
	    String username = null;
		if(authorizationHeade != null && authorizationHeade.startsWith("Bearer")) {
			token = authorizationHeade.substring(7);
			username = jwtUtil.extractUsername(token);
	}
		if(username!= null && SecurityContextHolder.getContext().getAuthentication()==null) {
		UserDetails userDetails =	service.loadUserByUsername(username);
		
        if (jwtUtil.validateToken(token, userDetails)) {

            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken
                    .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
		}
		filterChain.doFilter(request, response);
	}

}