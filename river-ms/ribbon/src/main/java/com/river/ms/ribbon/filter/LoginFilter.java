package com.river.ms.ribbon.filter;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginFilter implements Filter {

	private Map<String, String> initParameters = new HashMap<String, String>();

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		Enumeration<String> initParameterNames = filterConfig.getInitParameterNames();
		while (initParameterNames.hasMoreElements()) {
			String nextElement = initParameterNames.nextElement();
			this.initParameters.put(nextElement, filterConfig.getInitParameter(nextElement));
		}
	}

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);
		String requestURI = req.getRequestURI();
		if (session != null) {
			if (!this.isExist(requestURI)) {
				Object attribute = session.getAttribute("userInfo");
				if (attribute == null) {
					request.getRequestDispatcher("/system/user/pleaseLogin").forward(request, response);
					return;
				} else {
					Map<String, String> userInfo = (Map<String, String>) attribute;
					String isAuthentication = userInfo.get("isAuthentication");
					String isLogIn = userInfo.get("isLogIn");
					if (isAuthentication != null && isAuthentication.equals("true") && isLogIn != null
							&& isLogIn.equals("true")) {
						chain.doFilter(request, response);
					} else {
						request.getRequestDispatcher("/system/user/pleaseLogin").forward(request, response);
						return;
					}
				}
			} else {
				chain.doFilter(request, response);
			}
		} else {
			if (!this.isExist(requestURI)) {
				request.getRequestDispatcher("/system/user/pleaseLogin").forward(request, response);
				return;
			} else {
				chain.doFilter(request, response);
			}
		}

	}

	@Override
	public void destroy() {

	}

	public Boolean isExist(String requestURI) {
		boolean result = false;
		Set<Entry<String, String>> entrySet = this.initParameters.entrySet();
		for (Entry<String, String> entry : entrySet) {
			if (requestURI.startsWith(entry.getValue())) {
				return true;
			}
		}
		return result;
	}

}
