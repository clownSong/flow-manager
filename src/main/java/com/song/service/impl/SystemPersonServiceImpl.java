package com.song.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.song.model.SystemPersonModel;
import com.song.service.SystemPersonService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class SystemPersonServiceImpl implements SystemPersonService {
    private RestTemplate restTemplate = new RestTemplate();
    @Value("${flow-manager.get-person-url}")
    private String systemPersonUrl;

    @Override
    public List<SystemPersonModel> getByPerson(String id, String type) {

        try {
            String result = restTemplate.getForObject(systemPersonUrl + "?id=" + URLEncoder.encode(id, "UTF-8") + "&type=" + type, String.class);
            JsonMapper jsonMapper = new JsonMapper();
            Map<String, Object> r = jsonMapper.readValue(result, Map.class);
            List data = (List) r.get("data");

            List<SystemPersonModel> models = jsonMapper.readValue(jsonMapper.writeValueAsString(data), new TypeReference<List<SystemPersonModel>>() {
            });
            return models;
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
