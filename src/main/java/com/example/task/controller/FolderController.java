package com.example.task.controller;

import com.example.task.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/folder")
public class FolderController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/create")
    public ResponseEntity<String> createFolder(@RequestParam String spaceName, @RequestParam String folderName,@RequestParam String userEmail) {
        try {
            return ResponseEntity.ok(itemService.createFolder(spaceName,folderName,userEmail));
        }catch (Exception e){
            return ResponseEntity.ok("Can not create folder");
        }
    }
}

