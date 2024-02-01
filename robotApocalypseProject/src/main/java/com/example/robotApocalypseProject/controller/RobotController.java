package com.example.robotApocalypseProject.controller;

import com.example.robotApocalypseProject.model.Robots;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/robots")
public class RobotController
{
    private final String url = "https://robotstakeover20210903110417.azurewebsites.net/robotcpu";
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping
    public ResponseEntity<List<Robots>> getAllRobots() {
        List<Robots> robots = restTemplate.getForObject(url, List.class);

        return ResponseEntity.ok(robots);
    }
}
