package com.river.sso;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SSOServlet
 */
@WebServlet("/redirect.action")
public class SSOServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SSOServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		/*Enumeration<String> names = request.getHeaderNames();
		String name = null;
		while (names.hasMoreElements()) {
			name = names.nextElement();
			response.getWriter().append(name + ":");
			response.getWriter().append(request.getHeader(name) + "\n");
		}
		response.getWriter().append("x-forwarded-for:" + request.getHeader("x-forwarded-for") + "\n");
		response.getWriter().append("x-forwarded-for:" + request.getHeader("X-Forwarded-For") + "\n");
		response.getWriter().append("WL-Proxy-Client-IP:" + request.getHeader("WL-Proxy-Client-IP") + "\n");
		response.getWriter().append("Proxy-Client-IP:" + request.getHeader("Proxy-Client-IP")+"\n");
		response.getWriter().append("contextPath:" + request.getContextPath() + "\n");
		response.getWriter().append("remote_addr:" + request.getRemoteAddr() + "\n");
		response.getWriter().append("local_addr:" + request.getLocalAddr() + "\n");
		response.getWriter().append("remote_host:" + request.getRemoteHost() + "\n");
		response.getWriter().append("Served at: ").append(request.getContextPath() + "\n");
		String referer=request.getHeader("referer");
		response.getWriter().append("referer" + request.getHeader("referer") + "\n");
	   if(referer!=null && !referer.isEmpty()){
			InetAddress[] address = InetAddress.getAllByName(referer);
			for (int i = 0; i < address.length; i++) {
				response.getWriter().append("ip:address:"+address[i].getHostAddress()+"\n");
			}
		}*/
		String redirectUrl="http://come.enn.cn";
		response.setContentType("text/html;charset=utf-8");
		String ipAddress=request.getHeader("X-Forwarded-For");
		if(ipAddress==null || ipAddress.isEmpty()) {
			response.getWriter().append("没有权限访问，返回集团首页登录");
			response.sendRedirect(redirectUrl);
			return;
		}
		String[] ips={"10.37.144.166","10.37.32.195","10.37.32.199"};
		boolean isExist=false;
		for(int i=0;i<ips.length;i++){
			if(ips[i].equals(ipAddress)){
				isExist=true;
				if(i==0){
					redirectUrl="http://websealt.xinaogroup.com/mylogin.html";
				}
				break;
			}
		}
		String iv_name = request.getHeader("iv-user");
		if(iv_name==null || iv_name.isEmpty() || iv_name.equals("null") || iv_name.equals("Unauthenticated")) {
			response.getWriter().append("没有权限访问，请返回集团首页登录");
			response.sendRedirect(redirectUrl);
			return;
		}
		if(isExist){
			response.sendRedirect("/river/#/?from=enn&itcode="+iv_name);

		}else{
			response.getWriter().append("拒绝访问，请返回集团首页登录");
			response.sendRedirect(redirectUrl);
		}
		//response.getWriter().append("/river/?from=enn&itcode=" + iv_name);

		// request.getServletContext().getRequestDispatcher("/river?from=enn&itcode="+iv_name).forward(request,
		// response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
