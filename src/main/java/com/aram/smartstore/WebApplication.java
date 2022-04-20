package com.aram.smartstore;

import com.aram.smartstore.configuration.ApplicationConfiguration;
import com.aram.smartstore.configuration.WebConfiguration;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

public class WebApplication implements WebApplicationInitializer {

  @Override
  public void onStartup(ServletContext servletContext) {
    //ApplicationContext 생성 및 ROOT 컨테이너로 등록
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(ApplicationConfiguration.class);
    ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);

    //서블릿 컨테이너에 ContextLoaderListener 등록
    servletContext.addListener(contextLoaderListener);

    //디스패처 서블릿 생성
    DispatcherServlet dispatcherServlet = new DispatcherServlet();

    //WebContext 생성 (컨트롤러와 관련된 스프링 컨테이너)
    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
    webContext.setServletContext(servletContext);
    webContext.register(WebConfiguration.class);

    //디스패처 서블릿에 WebContext 등록
    dispatcherServlet.setApplicationContext(webContext);

    //서블릿 컨테이너에 디스패처 서블릿 등록
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet("dispatcher",
        dispatcherServlet);
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping("/");

    addFilter(servletContext);
  }

  private void addFilter(ServletContext servletContext) {
    FilterRegistration.Dynamic encodingFilter = servletContext
        .addFilter("characterEncodingFilter", new CharacterEncodingFilter("UTF-8", true, true));
    encodingFilter.addMappingForUrlPatterns(null, false, "/*");
  }

}
