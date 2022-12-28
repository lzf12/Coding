package com.controller;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.utils.JWTUtils;
import com.vo.User;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping( "/email-manager")
public class EmailController {


    @PostMapping("/v1/login")
    public String login(@RequestBody User user) {
        if ("zhangsan".equals(user.getUserName()) && "123456".equals(user.getPassword())) {
            Map<String, String> map = new HashMap<>();
            map.put("id", "123");
            map.put("username", user.getUserName());
            return JWTUtils.getToken(map);
        }
        return null;
    }

    @GetMapping("/v1/verify")
    public String login(String token) {
        DecodedJWT verify = JWTUtils.verify(token);

        return "true";
    }
}
