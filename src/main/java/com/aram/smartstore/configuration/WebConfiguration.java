package com.aram.smartstore.configuration;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.SimpleTimeZone;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperFactoryBean;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan({"com.aram.smartstore.**.controller"})
public class WebConfiguration implements WebMvcConfigurer {

  private static final String dateTimeFormat = "yyyy-MM-dd HH:mm:ss";

  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    Jackson2ObjectMapperFactoryBean jackson2ObjectMapperFactoryBean = new Jackson2ObjectMapperFactoryBean();

    jackson2ObjectMapperFactoryBean.setFailOnEmptyBeans(true);
    jackson2ObjectMapperFactoryBean.setIndentOutput(true);
    jackson2ObjectMapperFactoryBean.setFeaturesToDisable(
        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    jackson2ObjectMapperFactoryBean.setFeaturesToEnable(Feature.ALLOW_COMMENTS,
        Feature.ALLOW_SINGLE_QUOTES, DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);

    jackson2ObjectMapperFactoryBean.setDateFormat(new SimpleDateFormat(dateTimeFormat));
    jackson2ObjectMapperFactoryBean.setSerializers(new LocalDateTimeSerializer(
        DateTimeFormatter.ofPattern(dateTimeFormat)));
    jackson2ObjectMapperFactoryBean.setTimeZone(SimpleTimeZone.getTimeZone("UTC"));

    jackson2ObjectMapperFactoryBean.afterPropertiesSet();

    MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();
    httpMessageConverter.setObjectMapper(jackson2ObjectMapperFactoryBean.getObject());

    converters.add(httpMessageConverter);
  }
}
