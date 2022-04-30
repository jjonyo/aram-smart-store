package com.aram.smartstore.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Import({DbConfiguration.class})
@PropertySource("classpath:application.properties")
@Configuration
@ComponentScan({"com.aram.smartstore.**.service"})
public class ApplicationConfiguration {

}


