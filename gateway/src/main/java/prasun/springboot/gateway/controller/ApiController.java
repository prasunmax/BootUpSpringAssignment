package prasun.springboot.gateway.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import prasun.springboot.gateway.api.security.JWTUtil;

@RestController
@Configuration
@ComponentScan({"config", "entity", "security"})
public abstract class ApiController {

    @Autowired
    //protected TokenAuthenticationService tokenProvider;
    private JWTUtil util;

    @ResponseBody
    @GetMapping(value = "info")
    public ResponseEntity<Map<String, String>> info() {

        Map<String, String> responseMap = new HashMap<>();

        return ResponseEntity.ok(responseMap);
    }

    protected String getEmailFromRequest(String request) {
        return util.getUsernameFromToken(request);
    }

    @GetMapping(value = "/")
    abstract ResponseEntity<Map<String, String>> index();
}
