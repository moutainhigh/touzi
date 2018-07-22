package com.river.ms.business.task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.river.ms.business.entity.ProjectEmail;
import com.river.ms.business.service.MPProjectEmailService;

import freemarker.template.Template;

@Component
@Configurable
@EnableScheduling
public class EmailTask{


	@Autowired
	MPProjectEmailService remindService;
	@Autowired
	private JavaMailSenderImpl mailSender;

    @Value("${spring.mail.username}")
    private String Sender; //读取配置文件中的参数
    @Value("${spring.mail.test}")
    private boolean isTest;
    
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;  
    /**
     * 每1分钟执行一次
     * 
     **/
    @Scheduled(cron = "0 */1 *  * * * ")
    public void emailRemindByCron(){
        System.out.println ("Scheduling Tasks By Cron: The time is now " + dateFormat ().format (new Date ()));

        List<ProjectEmail> list=remindService.selectProjectEmail();
        
        for(int i=0;i<list.size();i++){
        	sendEmail(list.get(i));
        }
    }

    private SimpleDateFormat dateFormat(){
        return new SimpleDateFormat ("HH:mm:ss");
    }
    /**
     * 发送邮件
     * @param email
     */
    @SuppressWarnings("unchecked")
	private void sendEmail(ProjectEmail email){
    	if(email==null) return;
    	if(email.getResEmail()==null || email.getResEmail().isEmpty()) return;
    	if(isTest) email.setResEmail("22922803@qq.com");
    	
    	MimeMessage message = null;
		try {
			message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
			helper.setFrom(Sender);
			helper.setTo(email.getResEmail());
			helper.setSubject("[消息] 您有一个项目需要处理-新奥智慧投资系统");

			Map<String, Object> model = new HashedMap();
			model.put("userName", email.getResName());
			model.put("itcode", email.getItcode());
			model.put("projectTitle", email.getProjectTitle());
			model.put("projectId", email.getProjectId());
			model.put("createTime", email.getCreateTime());
			model.put("stage", email.getStage());

            FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();
            configurer.setTemplateLoaderPath("classpath:templates");
			Template template = freeMarkerConfigurer.getConfiguration().getTemplate("email.html");
			String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, model);
			helper.setText(html, true);
			System.out.println("发送邮件给："+email.getResEmail());
			mailSender.send(message);
        	remindService.updateById(email.getEntityId());
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}