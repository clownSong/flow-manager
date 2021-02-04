package com.song.web;

import com.song.model.SystemPersonModel;
import com.song.service.SystemPersonService;
import com.song.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author XiaoSong
 * @date 2019-12-21 13:26
 */
@RestController
@RequestMapping("/flowManager")
public class BaseController {
    @Autowired
    private TokenUtils tokenUtils;
    @Autowired
    private SystemPersonService personService;
    @GetMapping("/getToken")
    public String getToken() throws Exception {
        return tokenUtils.getToken();
    }
    @GetMapping("/refreshToken")
    public String refreshToken() throws Exception {
        return tokenUtils.getToken();
    }
    @GetMapping("/getUsers")
    public List<SystemPersonModel> getUsers(String type, String id){
        return personService.getByPerson(id,type);
    }
}
