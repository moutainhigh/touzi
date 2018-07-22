package com.river.ms.ribbon;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;


public class CrossFilter implements Filter {

	@Value("${skyline.crossfilter.originalURL}")
	private String originalURL;
	
	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		//res.setHeader("Access-Control-Allow-Origin", "*");
		res.setHeader("Access-Control-Allow-Origin", originalURL);
		//res.setHeader("Access-Control-Allow-Origin", "http://enn.xiangqiankan.net");
		res.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS, DELETE,PUT");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		chain.doFilter(request, response);

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}