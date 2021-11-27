package br.com.bandtec.osirisapi.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {

    @GetMapping("/ping")
    public ResponseEntity pingPongHealthCheck(){
        return ResponseEntity.status(200).body("pong");
    }
}