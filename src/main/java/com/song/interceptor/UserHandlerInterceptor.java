package com.song.interceptor;

import com.song.service.RedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by 96339 on 2018/6/17.
 *
 * @author xiaoSong
 * @date 2018/6/17
 */
@Component
public class UserHandlerInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisService redisService;

    /**
     * 在请求处理之前进行调用（Controller方法调用之前
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
       /* try {
            String token = httpServletRequest.getHeader("Authorization");
            if(StringUtils.isNotBlank(token)){
                token = token.replace("Bearer ","");
            }
            if (redisService.existsKey(token)) {
                return true;
            } else if (StringUtils.isNotBlank(token)) {
                httpServletResponse.sendError(402, "Authorization已过期");
            } else {
                httpServletResponse.sendError(402, "请在请求头中传入\"Authorization\"参数");
            }
            return false;
        } catch (Exception e) {
            httpServletResponse.sendError(500, e.getLocalizedMessage());
            return false;
        }*/
        return true;
    }

    /**
     * 请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
