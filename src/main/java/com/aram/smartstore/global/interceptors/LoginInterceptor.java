package com.aram.smartstore.global.interceptors;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_ID;
import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_SESSION_NAME;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoginInterceptor implements HandlerInterceptor {

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
      Object handler) {

    HttpSession session = request.getSession(false);

    if (session == null || session.getAttribute(LOGIN_SESSION_NAME) == null) {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      return false;
    }

    request.setAttribute(LOGIN_ID, session.getAttribute(LOGIN_SESSION_NAME));
    return true;
  }
}
