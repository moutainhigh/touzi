package com.river.business;

import java.util.Date;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.river.ms.business.BootApp;

import freemarker.template.Template;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = BootApp.class)
public class MailTest {
	@Autowired
	private JavaMailSenderImpl mailSender;


	    @Value("${spring.mail.username}")
	    private String Sender; //读取配置文件中的参数
	    @Autowired
	    private FreeMarkerConfigurer freeMarkerConfigurer;  
	 //@Test
	public void sendTemplateMail(){

		//通过文件获取信息    
		MimeMessage message = null;
		try {
			/*	message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo("22922803@qq.com");
			helper.setSubject("[消息] 您有一个项目需要处理-新奥智慧投资系统");

			Map<String, Object> model = new HashedMap();
			model.put("userName", "毛海嘉");
			model.put("itcode", "maohaijia");
			model.put("projectTitle", "测试项目");
			model.put("projectId", "250");
			model.put("stage", "赋能群核准");
			model.put("createTime", new Date());

            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("classpath:templates");
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			helper.setText(html, true);
			mailSender.send(message);*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
