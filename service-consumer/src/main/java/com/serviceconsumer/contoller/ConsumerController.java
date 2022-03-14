package com.serviceconsumer.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/service/v1")
public class ConsumerController {

    @Autowired
    DiscoveryClient discoveryClient;

    @GetMapping("/customers")
    public void getProductInfo(){
       List<ServiceInstance> list= discoveryClient.getInstances("service-producer");
       if(list!=null && list.size()>0) {
           String baseUrl= list.get(0).getUri().toString();
           RestTemplate restTemplate=new RestTemplate();
           ResponseEntity responseEntity;
           responseEntity=restTemplate.exchange(baseUrl+"/service/v1/products", HttpMethod.GET,getHeader(),String.class);
           System.out.println(responseEntity.getBody());
       }
    }

    private static HttpEntity<?> getHeader() {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.set("accept", MediaType.APPLICATION_JSON_VALUE);
        return new HttpEntity<>(httpHeaders);
    }

}
