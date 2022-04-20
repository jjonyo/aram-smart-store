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
    AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
    rootContext.register(ApplicationConfiguration.class);

    ContextLoaderListener contextLoaderListener = new ContextLoaderListener(rootContext);
    servletContext.addListener(contextLoaderListener);

    AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
    webContext.register(WebConfiguration.class);

    ServletRegistration.Dynamic dispatcher =
        servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
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
