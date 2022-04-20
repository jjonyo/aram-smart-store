package com.aram.smartstore.hello.controller;

import com.aram.smartstore.hello.mapper.HelloMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @Autowired
  HelloMapper helloMapper;

  @GetMapping("/")
  @ResponseBody
  public String test() {

    System.out.println("hello controller");
    return helloMapper.now();
  }
}
