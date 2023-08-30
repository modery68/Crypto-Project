package org.crypto.training.filter;

import org.crypto.training.service.JWTService;
import org.crypto.training.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.text.SimpleDateFormat;

@WebFilter(filterName = "logFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.REQUEST})
public class LogFilter implements Filter {
    @Autowired
    private JWTService jwtService;

    @Autowired
    private UserService userService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    private final List<String> excludedWords = Arrays.asList("newPasswd", "confirmPasswd", "passwd", "password");
    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss,SSS");

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        // Check null or not before use. Initialize dependency first then inject.
        if (jwtService == null) {
            // Once injected, it will inject all dependencies required by various filter instance.
            SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, servletRequest.getServletContext());
        }
        long startTime = System.currentTimeMillis();
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String logInfo = logInfo(req);
        filterChain.doFilter(servletRequest,servletResponse); //call filter over and over again until controller
        long endTime = System.currentTimeMillis();
        logger.info(logInfo.replace("responseTime", String.valueOf(System.currentTimeMillis() - startTime)));
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    private boolean isIgnoredWord(String word, List<String> excludedWords) {
        for (String excludedWord : excludedWords) {
            if (word.toLowerCase().contains(excludedWord)) return true;
        }
        return false;
    }

    private String logInfo(HttpServletRequest req) {
        String formData = null;
        String httpMethod = req.getMethod();

        Date startDateTime = new Date();
        String requestURL = req.getRequestURI();
        String userIP = req.getRemoteHost();
        String sessionID = req.getSession().getId();
        Enumeration<String> parameterNames = req.getParameterNames();
        List<String> parameters = new ArrayList();

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (isIgnoredWord(paramName, excludedWords)) continue;

            String paramValues = Arrays.asList(req.getParameterValues(paramName)).toString();
            parameters.add(paramName + "=" + paramValues);
        }

        if (!parameters.isEmpty()) {
            formData = parameters.toString().replaceAll("^.|.$", "");
        }

        return  new StringBuilder("###@@@$$$  ")
                .append("| ")
                .append(formatter.format(startDateTime)).append(" | ")
                .append(userIP).append(" | ")
                .append(httpMethod).append(" | ")
                .append(requestURL).append(" | ")
                .append(sessionID).append(" | ")
                .append("responseTime ms").append(" | ")
                .append(formData).toString();
    }
}
