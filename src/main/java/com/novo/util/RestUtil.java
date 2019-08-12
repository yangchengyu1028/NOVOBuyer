package com.novo.util;


import java.util.Iterator;
import java.util.Map;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@Component
public class RestUtil {
	private  final static Logger log = LoggerFactory.getLogger(RestUtil.class);
	
	private String url;
	
	@Autowired
	private RestTemplate restTemplate;
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 
	 * @param uri controller地址
	 * @param httpHeaders 请求头(传入null时，默认请求格式为json）
	 * @param httpMethod 请求方式（传入null时，默认请求为GET）
	 * @param params 键值对（@RequestParam  需要传入的参数） 
	 * @return
	 */
	public JSONObject restRequest(String uri , HttpHeaders httpHeaders,HttpMethod httpMethod,Map<String,String> params) {
		String goal = url + uri;
		log.info("Request_URL >= "+goal);
		goal = goal + "?";
		Iterator<Map.Entry<String, String>> it= params.entrySet().iterator();
		ObjectMapper mapper = new ObjectMapper();
	    while(it.hasNext()){
		     Map.Entry<String, String> map =it.next();
	         goal = goal + map.getKey()+"={"+map.getKey()+"}&";
		   }
	    
	    String json = null;
		try {
			json = mapper.writeValueAsString(params);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.info(e.toString());
			e.printStackTrace();
			return null;
		}
	    
	    goal = goal.substring(0, goal.length()-1);
	    if(null == httpHeaders || StringUtils.isEmpty(httpHeaders)) {
	    	httpHeaders = new HttpHeaders();
	    	httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
	    }
	    if(null == httpMethod || StringUtils.isEmpty(httpMethod)) {
	    	httpMethod = HttpMethod.GET;
	    }
	    
	    log.info("Request_Full_URL >= "+goal);
	    
		@SuppressWarnings({ "rawtypes", "unchecked" })
		HttpEntity httpEntity = new HttpEntity(json,httpHeaders);
		
		log.info("Request_Entity >= "+httpEntity);
			
		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(goal, httpMethod, httpEntity, JSONObject.class, params);
		
		log.info("Result_ResponseEntity >= "+responseEntity);
		
		return responseEntity.getBody();
	}
}

@Configuration
class  RestTemplateConfig {
	@Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        return new RestTemplate(factory);
    }
	
	 @Bean
	    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
	        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
	        factory.setReadTimeout(5000);//单位为ms
	        factory.setConnectTimeout(5000);//单位为ms
	        return factory;
	    }

}