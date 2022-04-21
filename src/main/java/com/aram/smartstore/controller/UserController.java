package com.aram.smartstore.controller;

import com.aram.smartstore.controller.dto.request.LoginUserRequestDto;
import com.aram.smartstore.controller.dto.request.SaveUserRequestDto;
import com.aram.smartstore.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {

  private final UserService userService;

  @PostMapping("/signup")
  public ResponseEntity<Long> signUp(@RequestBody SaveUserRequestDto saveUserRequestDto) {
    Long savedId = userService.saveUser(saveUserRequestDto);

    return new ResponseEntity<>(savedId, HttpStatus.OK);
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody LoginUserRequestDto loginUserRequestDto,
      HttpServletResponse response) {
    Long userId = userService.loginUser(loginUserRequestDto.getUsername(),
        loginUserRequestDto.getPassword());

    Cookie cookie = new Cookie("login-token", userId.toString());
    cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
    response.addCookie(cookie);

    return new ResponseEntity<>("Login SUCCESS", HttpStatus.OK);
  }
}
