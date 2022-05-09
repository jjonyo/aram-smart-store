package com.aram.smartstore.configuration;

import com.aram.smartstore.global.interceptors.LoginInterceptor;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SimpleTimeZone;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.aram.smartstore.**.controller"})
public class WebConfiguration implements WebMvcConfigurer {

  private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        .addPathPatterns("/**")
        .excludePathPatterns("/", "/auth/signup", "/auth/login");
  }

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();

    jackson2ObjectMapperFactoryBean.setIndentOutput(true);
    jackson2ObjectMapperFactoryBean.setTimeZone(SimpleTimeZone.getTimeZone("UTC"));
    jackson2ObjectMapperFactoryBean.setSerializers(new LocalDateTimeSerializer(
        DateTimeFormatter.ofPattern(dateTimeFormat)));

    jackson2ObjectMapperFactoryBean.afterPropertiesSet();

    MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
    httpMessageConverter.setObjectMapper(jackson2ObjectMapperFactoryBean.getObject());

    converters.add(httpMessageConverter);
  }
}
