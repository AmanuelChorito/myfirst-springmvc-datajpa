package com.example.springclient.service;

import com.example.springclient.model.CountryRegion;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Service
public class CountryRegionServiceClient {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${spring-boot-server.name}")
    private String serverName;


    @Value("${Region-service.username}")
    private String username;
    @Value("${Region-service.password}")
    private String password;

    HttpHeaders createHttpHeaders() {
        return new org.springframework.http.HttpHeaders() {{
            String auth = username + ":" + password;
            byte[] encodedAuth = Base64.encodeBase64(
                    auth.getBytes(Charset.forName("US-ASCII")));
            String authHeader = "Basic " + new String(encodedAuth);
            set("Authorization", authHeader);
        }};
    }

    String getBaseServiceurl() {
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
        serviceInstances.forEach(System.out::println);

        return serviceInstances.get(0).getUri().toString();
    }

    public List<CountryRegion> findAll() {
        ResponseEntity<CountryRegion[]> countryRegions = restTemplate.exchange(getBaseServiceurl() + "/countries", HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), CountryRegion[].class);
        // restTemplate.getForObject(baseurl+"/countries",CountryRegion[].class);
        assert countryRegions != null;
        return Arrays.asList(countryRegions.getBody());
    }


    public ResponseEntity<CountryRegion> findById(String countryCode) {
        String url = getBaseServiceurl() + "/countries/" + countryCode;
        return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity<>(createHttpHeaders()), CountryRegion.class);
    }
}
