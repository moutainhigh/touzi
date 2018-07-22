package com.river.ms.system;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.river.core.filter.RiverCrossFilter;
import com.river.core.filter.RiverLogFilter;
import com.river.ms.system.webService.organizationWB.OrganizationWBService;
import com.river.ms.system.webService.organizationWB.OrganizationWBServiceImpl;
import com.river.ms.system.webService.userWB.UserWBService;
import com.river.ms.system.webService.userWB.UserWBServiceImpl;

import javax.xml.ws.Endpoint;

import org.apache.cxf.Bus;
import org.apache.cxf.bus.spring.SpringBus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

@Configuration
public class BootConfig {

	@Value("${riverLogFilter.logIpAddress}")
	private String logIpAddress;

	@Value("${riverLogFilter.port}")
	private int port;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings("/druid/*");
		reg.addInitParameter("loginUsername", "druid");
		reg.addInitParameter("loginPassword", "huangping");
		return reg;
	}

	/**
	 * 
	 * @return
	 */
	/*@Bean
	public FilterRegistrationBean crossServlet() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new RiverCrossFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}*/

	/**
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		filterRegistrationBean.addInitParameter("principalCookieName", "USER_COOKIE");
		filterRegistrationBean.addInitParameter("principalSessionName", "USER_SESSION");
		return filterRegistrationBean;
	}

	/**
	 * 配置日志Filter
	 * 
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRiverLogFilterBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new RiverLogFilter(this.getLogIpAddress(), this.getPort()));
		filterRegistrationBean.addUrlPatterns("/*");
		return filterRegistrationBean;
	}

	/**
	 * 配置CXF
	 * @return
	 */
	@Bean
	public ServletRegistrationBean cxfServlet() {
		return new ServletRegistrationBean(new CXFServlet(), "/enn/*");
	}

	@Bean(name = Bus.DEFAULT_BUS_ID)
	public SpringBus springBus() {
		return new SpringBus();
	}

	/**
	 * organization webservice配置
	 * @return
	 */
	@Bean
	public OrganizationWBService organizationWBService() {
		return new OrganizationWBServiceImpl();
	}
	
	@Bean
	public Endpoint organizationWBServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), organizationWBService());
		endpoint.publish("organizationWB");
		return endpoint;
	}
	
	/**
	 * user webservice配置
	 * @return
	 */
	@Bean
	public UserWBService userWBService() {
		return new UserWBServiceImpl();
	}
	
	@Bean
	public Endpoint userWBServiceEndpoint() {
		EndpointImpl endpoint = new EndpointImpl(springBus(), userWBService());
		endpoint.publish("userWBService");
		return endpoint;
	}

	public String getLogIpAddress() {
		return logIpAddress;
	}

	public void setLogIpAddress(String logIpAddress) {
		this.logIpAddress = logIpAddress;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
