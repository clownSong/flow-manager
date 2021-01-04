package com.song.interceptor;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.song.model.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Objects;

@Component
public class HandlerExceptionImpl implements HandlerExceptionResolver {
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {

        try {
            httpServletResponse.setContentType("application/json;charset=utf-8");
            OutputStream os = httpServletResponse.getOutputStream();
            StackTraceElement[] elements = e.getStackTrace();
            String errorMethod = "";
            if (!Objects.isNull(elements)) {
                for (int i = (elements.length - 1); i >= 0; i--) {
                    if (elements[i].getClassName().contains("com.song")) {
                        errorMethod = elements[i].getClassName() + "类,方法：" + elements[i].getMethodName() + ":行数：" + elements[i].getLineNumber();
                        break;
                    }
                }
            }
            os.write(new JsonMapper().writeValueAsBytes(Result.error(errorMethod, "错误信息：" + e.getMessage(), httpServletResponse.getStatus())));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return new ModelAndView();
    }
}
