package bg.infosys.daeu.ws.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IISDAControllerAccessInterceptor implements HandlerInterceptor {

    private boolean inUse = false;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!inUse) {
            inUse = true;
        } else {
            response.sendError(HttpServletResponse.SC_CONFLICT, "currently in use");
        }

        return inUse;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        inUse = false;
    }
}
