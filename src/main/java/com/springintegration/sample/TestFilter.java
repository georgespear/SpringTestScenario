package com.springintegration.sample;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import junit.framework.Assert;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.GenericFilterBean;

public class TestFilter extends GenericFilterBean {

	@Autowired
	SessionService clientSessionService;
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String requestSessionId =  ((HttpServletRequest) request).getSession().getId();
		String serviceSessionId = clientSessionService.getSessionId();
		System.err.println("Session id from request: " + requestSessionId);
		System.err.println("Session id from service : " + serviceSessionId);
		if (!requestSessionId.equals(serviceSessionId))
			throw new ServletException("Session is invalid!");
		
	}

}
