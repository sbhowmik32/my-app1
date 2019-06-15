package wizard.authentication.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.DispatcherType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class Interceptor extends HandlerInterceptorAdapter {
    private static final Logger log = LoggerFactory.getLogger(Interceptor.class);

    @Autowired
    public Interceptor() {
    }

    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler
    ) {
        if(!request.getDispatcherType().equals(DispatcherType.REQUEST)){
            return true;
        }
        return true;
    }

}
