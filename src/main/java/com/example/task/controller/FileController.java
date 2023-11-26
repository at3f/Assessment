package com.example.task.controller;

import com.example.task.dto.FileMetadata;
import com.example.task.service.FileService;
import com.example.task.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/file")
public class FileController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;

    @PostMapping("/create")
    public ResponseEntity<String> createFile(@RequestParam String folderName,@RequestParam String userEmail,@RequestParam MultipartFile file) throws IOException {
        try {
            return ResponseEntity.ok(itemService.createFile(folderName,userEmail,file));
        }catch (Exception e){
            return ResponseEntity.ok("Can not create file");
        }
    }

    @GetMapping("/metadata")
    public ResponseEntity<FileMetadata> viewFileMetadata(@RequestParam Long fileId, @RequestParam String userEmail) {
        FileMetadata metadata = null;
        try {
            metadata = fileService.getFileMetadata(fileId, userEmail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return ResponseEntity.ok(metadata);
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadFile(@RequestParam Long fileId, @RequestParam String userEmail) {
        ResponseEntity<byte[]> response = null;
        try {
            response = fileService.downloadFile(fileId, userEmail);
        }catch (Exception e){
            e.printStackTrace();
        }
        return response;
    }
}

