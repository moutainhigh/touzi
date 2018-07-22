package com.river.ms.ribbon;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by Yinovo on 2017-05-22.
 */
public class AccessFilter extends ZuulFilter {
	private final Logger log = Logger.getLogger(getClass());

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * true启用过滤拦截，正式环境必须启用
	 */
	@Override
	public boolean shouldFilter() {
		return false;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		log.info(String.format("%s request to %s", request.getMethod(), request.getRequestURL().toString()));
		String accessToken = request.getParameter("accessToken");
		System.out.println("accessToken:"+accessToken);
		if (accessToken == null) {
			log.warn("access token is empty");
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
		}
		HttpSession session = ctx.getRequest().getSession();
		String sToken = (String) session.getAttribute("accessToken");
		if (!accessToken.equals(sToken)) {
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
		}
		return null;
	}
}