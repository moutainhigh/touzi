package com.river.ms.ribbon;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Yinovo on 2017-05-22.
 */
@Service
public class TokenService {
    @Autowired
    RestTemplate restTemplate;
    /**
     * 临时处理方式，后续再调整
     * @return
     */
    @HystrixCommand(fallbackMethod = "getTokenFallback")
    public String getAccessToken() {
    	String token=generateGUID();
        return token;
    }
    /**
     * 临时处理方式，后续再完善调整
     * @return
     */
    public String getTokenFallback() {
        return "error:yinovo.com";
    }
    public static void main(String[] args) {
    	String generateGUID = generateGUID();
    	System.out.println(generateGUID);
	}
    public static String generateGUID(){
    	UUID guid=java.util.UUID.randomUUID();
    	return guid.toString().replaceAll("\\-", "");
    }
}