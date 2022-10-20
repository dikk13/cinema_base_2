package com.kata.cinema.base.security;

import com.kata.cinema.base.models.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    private final UserDetailsServiceImpl userDetailsService;

    public UserValidator(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        System.out.println("CP VALIDATE");
        System.out.println("username now is " + user.getUsername());
        try {
            userDetailsService.loadUserByUsername(user.getUsername());
            System.out.println("loading user by username done");
        } catch (UsernameNotFoundException ignored) {
            System.out.println("NO SUCH USER");
            return;
        }
        System.out.println("CP VALIDATE2");
        errors.rejectValue("email", "", "Человек с таким именем пользователя уже существует");
        System.out.println("CP VALIDATE3");
    }
}