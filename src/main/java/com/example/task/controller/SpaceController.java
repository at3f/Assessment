package com.example.task.controller;

import com.example.task.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/space")
public class SpaceController {
    @Autowired
    private ItemService itemService;
    @PostMapping("/create")
    public ResponseEntity<String> createSpace() {
        try {
            itemService.createSpace("stc-assessments", "admin");
            return ResponseEntity.ok("Space created successfully");
        }catch (Exception e){
            return ResponseEntity.ok("Can not create space");
        }

    }
}

