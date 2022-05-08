package com.aram.smartstore.user.controller;

import com.aram.smartstore.user.controller.dto.LoginUserRequestDto;
import com.aram.smartstore.user.controller.dto.SaveUserRequestDto;
import com.aram.smartstore.user.service.UserService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private static final String LOGIN_TOKEN_NAME = "login-token";
  private static final String LOGIN_SUCCESS_MESSAGE = "Login Success";

  @PostMapping("/auth/signup")
  public ResponseEntity<Long> signUp(@RequestBody SaveUserRequestDto saveUserRequestDto) {
    Long savedId = userService.saveUser(saveUserRequestDto);

    return new ResponseEntity<>(savedId, HttpStatus.OK);
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginUserRequestDto loginUserRequestDto,
      HttpServletResponse response) {
    Long userId = userService.loginUser(loginUserRequestDto.getUsername(),
        loginUserRequestDto.getPassword());

    Cookie cookie = new Cookie(LOGIN_TOKEN_NAME, userId.toString());
    cookie.setMaxAge(7 * 24 * 60 * 60); // 7 days
    response.addCookie(cookie);

    return new ResponseEntity<>(LOGIN_SUCCESS_MESSAGE, HttpStatus.OK);
  }
}
