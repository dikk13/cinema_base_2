package com.kata.cinema.base.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Cryptor {
    public BCryptPasswordEncoder getCryptor() {
        return new BCryptPasswordEncoder(5);
    }
}
