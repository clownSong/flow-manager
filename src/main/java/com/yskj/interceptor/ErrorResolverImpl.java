package com.yskj.interceptor;

import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Component
public class ErrorResolverImpl implements ErrorViewResolver {
    @Override
    public ModelAndView resolveErrorView(HttpServletRequest request, HttpStatus status, Map<String, Object> model) {
        ModelAndView mav = new ModelAndView("error/" + status.value() + "");
        mav.addAllObjects(model);
        return mav;
    }
}
