package com.song;

import com.alibaba.fastjson.JSON;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author XiaoSong
 * @date 2019-12-18 14:45
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class FlowManagerApplicationTest {
    public MockMvc mockMvc;
    @Autowired
    private WebApplicationContext wac;
    public Map<String, String> param = new HashMap<>();


    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    public void get(String url, Map<String, String> param) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(url);
        test(param, request);
    }

    public void post(String url, Map<String, String> param) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(url);
        test(param, request);
    }

    public void put(String url, Map<String, String> param) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(url);
        test(param, request);
    }

    public void delete(String url, Map<String, String> param) throws Exception {
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(url);
        test(param, request);
    }

    private void test(Map<String, String> param, MockHttpServletRequestBuilder request) throws Exception {
        Iterator<String> iterator = param.keySet().iterator();
        String key;
        while (iterator.hasNext()) {
            key = iterator.next();
            request.param(key, param.get(key));
        }
        request.characterEncoding("UTF-8");
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }

    public void print(Object object) {
        System.out.println(JSON.toJSONString(object));
    }
}
