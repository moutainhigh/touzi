package com.river.sso;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Map;
import java.util.Vector;

import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*import org.apache.commons.collections.map.HashedMap;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;*/
import lotus.domino.NotesFactory;
import lotus.domino.Session;
import lotus.domino.Database;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.cso.View;



/**
 * Servlet implementation class MailServlet
 */
@WebServlet("/MailServlet")
public class MailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		testReceiveMail(response.getWriter());
		response.getWriter().append("send finished at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public void testReceiveMail(PrintWriter writer){  
	    String dominoServer = "smtp.xinaogroup.com";  
	    String username = "ifn";  
	    Session session = null;  
	    Database database = null;  
	    View view = null;  
	    try {  
	        session = NotesFactory.createSession(dominoServer, NotesFactory.createORB(), username, "B-AU966G");  
	        writer.append("connect to mail server ok session" );  
	        database = session.getDatabase(session.getServerName(), "mail//" + username + ".nsf", false);  
	        writer.append("connect to mail server ok 2 database" );  
	        Document mailDom = database.createDocument(); 
            mailDom.replaceItemValue("Form","ifn@enn.cn");     
            mailDom.replaceItemValue("Subject","testmail");  
            mailDom.replaceItemValue("Body","bodycontent");  
            Vector<String>  recipientsVector = new Vector<String>();  
            String recipients="22922803@qq.com";
            String [] recipientArray = recipients.split(",");  
            System.out.print("send to: ");  
            for(String rept:recipientArray){  
                recipientsVector.add(rept);  
                System.out.print(rept+"  ");  
            }  
            mailDom.save();  
	        writer.append("connect to mail server ok save document" );  
            mailDom.send(recipientsVector);              
	        writer.append("connect to mail server ok send" );  

	        /*view = database.getView("($Inbox)");  
	        //获得第一封邮件的unid  
	        Document doc = view.getFirstDocument();  
	        String unid = doc.getUniversalID();  
	        //通过具体的unid，获得对应的邮件信息  
	        doc = database.getDocumentByUNID(unid);  
	        System.out.println(doc.generateXML());  
	          
	        System.out.println("mail from:" + doc.getItemValueString("Principal"));  
	        System.out.println("mail content:" + doc.getItemValueString("Body"));  
	        System.out.println("mail subject:" + doc.getItemValueString("Subject"));  */
	        //处理附件  
	        //saveAttachment(doc);  
	    } catch (Exception ex) {  
	        System.out.println("connect to mail server fail:" +ex.getMessage());
	        writer.append("connect to mail server fail:"+ex.getMessage() );  
	        ex.printStackTrace();
	    }finally{  
	        if(view!=null){  
	            try {  
	                view.recycle();  
	            } catch (NotesException e) {  
	    	        writer.append("connect to mail server fail:"+e.getMessage() );  
	                e.printStackTrace();  
	            }  
	        }  
	        if(database!=null){  
	            try {  
	                database.recycle();  
	            } catch (NotesException e) {  
	            	writer.append("connect to mail server fail:"+e.getMessage() );
	            	e.printStackTrace();  
	            }  
	        }  
	        if(session!=null){  
	            try {  
	                session.recycle();  
	            } catch (NotesException e) {  
	            	writer.append("connect to mail server fail:"+e.getMessage() );
	                e.printStackTrace();  
	            }  
	        }  
	    }  
	}  
	public void sendTest(PrintWriter writer){
	MimeMessage message = null;
	/*	try {
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
		mailSender.send(message);*/
	}
}
