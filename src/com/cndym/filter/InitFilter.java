package com.cndym.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cndym.entity.user.User;
import com.cndym.util.export.Utils;

public class InitFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;

		HttpSession session = request.getSession();
		session.setAttribute("ctx", request.getContextPath());
		String url = request.getRequestURI();
		if(url.startsWith("//")){
			url = url.substring(1, url.length());
		}
		if(checkPass(url,pass)){
			chain.doFilter(request, response);
			return;
		}
		
		User user = (User) request.getSession().getAttribute("LOGON_USER");
		if (!Utils.isNotEmpty(user)) {
			request.setAttribute("mes", "ÇëµÇÈë");
			request.getRequestDispatcher("/login/toHome.do").forward(request, response);
			return;
		}

		chain.doFilter(request, response);
	}
	
	private static String[] pass = {
		//µÇÂ¼
		".*/login/login.do",".*/login/toHome.do",
		"/",
		};
	
	private boolean checkPass(String uri,String[] uris){
		for(String p : uris){
			if(uri.matches(p)){
				return true;
			}
		}
		return false;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

}
