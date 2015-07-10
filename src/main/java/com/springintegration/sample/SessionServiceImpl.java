package com.springintegration.sample;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "session", proxyMode = ScopedProxyMode.INTERFACES)
public class SessionServiceImpl implements SessionService {
	
	@Autowired
	private HttpServletRequest request;
	
	public String getSessionId() {
		String sessionId = request.getSession().getId();
		return sessionId;
	}

}
