package com.kata.cinema.base.webapp.controllers.authentication;

import com.kata.cinema.base.dao.abstracts.RegistrationUserDao;
import com.kata.cinema.base.dto.AuthRequestDto;
import com.kata.cinema.base.dto.UserRegistrationRequestDto;
import com.kata.cinema.base.mappers.UserMapper;
import com.kata.cinema.base.models.User;
import com.kata.cinema.base.security.UserValidator;
import com.kata.cinema.base.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AuthRegistrationRequestController {

    private final UserMapper userMapper;
    private final RegistrationUserDao registrationUserDao;
    private final JwtUtil jwtUtil;
    private final UserValidator userValidator;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthRegistrationRequestController(UserMapper userMapper, RegistrationUserDao registrationUserDao, JwtUtil jwtUtil, UserValidator userValidator, AuthenticationManager authenticationManager) {
        this.userMapper = userMapper;
        this.registrationUserDao = registrationUserDao;
        this.jwtUtil = jwtUtil;
        this.userValidator = userValidator;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationRequestDto> registrationForm(@RequestBody UserRegistrationRequestDto requestDto, BindingResult result ) {
        User user = userMapper.toUser(requestDto);
      userValidator.validate(user, result);
      try {
          if (!result.hasErrors()) {
            registrationUserDao.register(user);
          }
      } catch (AuthenticationException e) {
        throw new BadCredentialsException("Registration error");
      }
      return ResponseEntity.ok(requestDto);
    }


    @PostMapping("/token")
    public ResponseEntity<Map<String, Object>> authLogin(@RequestBody AuthRequestDto authRequestDto) {
      UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(authRequestDto.getUsername(), authRequestDto.getPassword());
      try {
        authenticationManager.authenticate(authenticationToken);
      } catch (AuthenticationException e) {
        throw new BadCredentialsException("Invalid username or password");
      }
      String token =  jwtUtil.generateToken(authRequestDto.getUsername());

      Map<String, Object> response = new HashMap<>();
      response.put("email", authRequestDto.getUsername());
      response.put("token", token);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        System.out.println(SecurityContextHolder.getContext());
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication.getPrincipal());
//        System.out.println(authentication.getCredentials());
//        System.out.println(authentication.isAuthenticated());
      return ResponseEntity.ok(response);
    }
}


