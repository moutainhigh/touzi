package com.river.ms.dfs;
import org.springframework.boot.builder.SpringApplicationBuilder;  
  
public class ServletInitializer extends org.springframework.boot.web.support.SpringBootServletInitializer {  
  
      
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(this.getClass());  
    }  
  
}  