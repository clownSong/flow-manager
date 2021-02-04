package com.song.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.song.model.SystemPersonModel;
import com.song.service.SystemPersonService;
import com.song.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemPersonServiceImpl implements SystemPersonService {
    @Value("${flow-manager.get-person-url}")
    private String systemPersonUrl;
    @Autowired
    private TokenUtils tokenUtils;

    @Override
    public List<SystemPersonModel> getByPerson(String id, String type) {

        try {
            /*RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(systemPersonUrl + "?id=" + URLEncoder.encode(id, "UTF-8") + "&type=" + type, String.class);*/
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.add("Authorization",tokenUtils.getToken());
            HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
            ResponseEntity result = restTemplate.exchange(systemPersonUrl+ "?id=" + URLEncoder.encode(id, "UTF-8") + "&type=" + type, HttpMethod.GET,requestEntity,String.class);
            if(result.getStatusCode() == HttpStatus.OK){
                JsonMapper jsonMapper = new JsonMapper();
                Map<String, Object> r = jsonMapper.readValue(result.getBody().toString(), Map.class);
                List data = (List) r.get("data");

                List<SystemPersonModel> models = jsonMapper.readValue(jsonMapper.writeValueAsString(data), new TypeReference<List<SystemPersonModel>>() {
                });
                return models;
            }
            return new ArrayList<>();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
