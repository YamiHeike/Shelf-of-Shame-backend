package com.example.shelfofshame.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class DotenvEnvironmentPostProcessor implements EnvironmentPostProcessor {
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        Dotenv dotenv = Dotenv.load();
        Map<String, Object> envMap = new HashMap<>();
        envMap.put("jwt.secret", dotenv.get("jwt.secret"));
        envMap.put("jwt.expirationMs", dotenv.get("jwt.expirationMs"));

        environment.getPropertySources().addLast(new MapPropertySource("dotenvProperties", envMap));
    }
}