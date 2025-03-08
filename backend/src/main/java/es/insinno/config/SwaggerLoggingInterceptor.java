package es.insinno.config;

import es.insinno.entity.SwaggerLog;
import es.insinno.repository.SwaggerLogRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import java.time.LocalDateTime;

@Component
public class SwaggerLoggingInterceptor implements HandlerInterceptor {

    @Autowired
    private SwaggerLogRepository logRepository;

    private final ThreadLocal<SwaggerLog> currentLog = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SwaggerLog log = new SwaggerLog();
        log.setMethod(request.getMethod());
        log.setUri(request.getRequestURI());
        log.setRemoteAddress(request.getRemoteAddr());
        log.setTimestamp(LocalDateTime.now());
        currentLog.set(log);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        SwaggerLog log = currentLog.get();
        log.setResponseStatus(response.getStatus());
        logRepository.save(log);
        currentLog.remove();
    }
}