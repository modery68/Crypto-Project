package org.crypto.training.filter;
import io.jsonwebtoken.Claims;
import org.crypto.training.model.System_User;
import org.crypto.training.service.JWTService;
import org.crypto.training.service.System_UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SecurityFilter implements Filter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private System_UserService system_userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final Set<String> ALLOWED_PATH = new HashSet<>(Arrays.asList("", "/login", "logout", "register"));

    private static final Set<String> IGNORED_PATH = new HashSet<>(Arrays.asList("/auth"));

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("Start to do authorization");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        int statusCode = authorization(req);
        if (statusCode == HttpServletResponse.SC_ACCEPTED) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            ((HttpServletResponse)servletResponse).sendError(statusCode);
        }
    }

    private int authorization(HttpServletRequest req) {
        int statusCode = HttpServletResponse.SC_UNAUTHORIZED;
        String url = req.getRequestURI();
        if (IGNORED_PATH.contains(url)) {
            return HttpServletResponse.SC_ACCEPTED;
        }

        try {
            String token = req.getHeader("Authorization").replaceAll("^(.*?)", "");
            if (token == null || token.isEmpty()) {
                return statusCode;
            }

            Claims claims = jwtService.decryptToken(token);
            logger.info("===== after parsing JWT token, claims.get()={}", claims.getId());

            if (claims.getId() != null) {
                System_User s = system_userService.getSystem_UserById(Long.valueOf(claims.getId()));
                if (s != null) {
                    statusCode = HttpServletResponse.SC_ACCEPTED;
                }
            }
        }catch (Exception e) {
            logger.info("Cannot get token");
        }
        return statusCode;
    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
