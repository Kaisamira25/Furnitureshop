package com.poly.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dypp55fbk");
        config.put("api_key", "214414611585452");
        config.put("api_secret", "4VeEezVE_n2C91mVAyfyTkbGbVw");
        return new Cloudinary(config);
    }
}

