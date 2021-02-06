package com.song.utils;

import com.song.model.AppClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Objects;

@Component
public class TokenUtils {
    @Value("${flow-manager.client-url}")
    private String url;
    @Value("${flow-manager.token-verify}")
    private String tokenVerify;
    public static String token;

    /**
     * 获取应用token
     *
     * @return
     */
    public synchronized String getToken() throws Exception {
        if("0".equals(tokenVerify)){
            return "";
        }
        if (!Objects.isNull(token)) {
            if (verifyToken(token)) {
                return token;
            }
        }
        RestTemplate restTemplate = new RestTemplate();
        AppClient client = new AppClient();
        client.setAppId("2");
        client.setAppSecret(RSAEncrypt.encode("321123"));
        Map<String, Object> result = restTemplate.postForObject(
                url + "/client/login", client, Map.class);
        if (result.get("code").toString().equals("200")) {
            token = "Bearer " + result.get("msg").toString();
            return token;
        }
        return null;
    }

    /**
     * 验证token是否有效
     *
     * @param token
     * @return
     */
    public boolean verifyToken(String token) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Authorization", token);
        HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(url + "/refreshToken", HttpMethod.POST, requestEntity, String.class);
        } catch (RestClientException e) {
            return false;
        }
        return true;
    }
}
