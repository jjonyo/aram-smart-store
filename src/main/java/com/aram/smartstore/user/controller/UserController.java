package com.aram.smartstore.user.controller;

import static com.aram.smartstore.global.constants.SessionConstants.LOGIN_SESSION_NAME;

import com.aram.smartstore.user.controller.dto.LoginUserRequestDto;
import com.aram.smartstore.user.controller.dto.SaveUserRequestDto;
import com.aram.smartstore.user.service.UserService;
import javax.servlet.http.HttpSession;
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
  private static final String LOGIN_SUCCESS_MESSAGE = "로그인 성공";

  @PostMapping("/auth/signup")
  public ResponseEntity<Long> signUp(@RequestBody SaveUserRequestDto saveUserRequestDto) {
    Long savedId = userService.saveUser(saveUserRequestDto);

    return new ResponseEntity<>(savedId, HttpStatus.OK);
  }

  @PostMapping("/auth/login")
  public ResponseEntity<?> login(@RequestBody LoginUserRequestDto loginUserRequestDto,
      HttpSession httpSession) {
    Long userId = userService.loginUser(loginUserRequestDto.getUsername(),
        loginUserRequestDto.getPassword());

    httpSession.setAttribute(LOGIN_SESSION_NAME, userId);

    return new ResponseEntity<>(LOGIN_SUCCESS_MESSAGE, HttpStatus.OK);
  }
}
