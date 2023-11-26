package com.example.task.service;

import com.example.task.dto.FileMetadata;
import com.example.task.model.File;
import com.example.task.model.Item;
import com.example.task.model.Permission;
import com.example.task.repository.FileRepository;
import com.example.task.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;

    @Autowired
    private ItemRepository itemRepository;

    public void saveFile(MultipartFile multipartFile,Item item) throws IOException {
        File file = new File();
        file.setItem(item);
        file.setBinary(multipartFile.getBytes());
        fileRepository.save(file);
    }

    public FileMetadata getFileMetadata(Long fileId, String userEmail) {
        Item file = itemRepository.findById(fileId)
                .orElseThrow(() -> new EntityNotFoundException("File not found: " + fileId));

        if(!checkUserHasAccessToFile(file,userEmail)){
            throw new RuntimeException(userEmail+" has no permission");
        }

        return new FileMetadata(file.getId(), file.getName(), file.getFile().getBinary());
    }

    public ResponseEntity<byte[]> downloadFile(Long fileId, String userEmail) {
        Item file = itemRepository.findById(fileId)
                .orElseThrow(() -> new EntityNotFoundException("File not found: " + fileId));

        if(!checkUserHasAccessToFile(file,userEmail)){
            throw new RuntimeException(userEmail+" has no permission");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDisposition(ContentDisposition.builder("pdf").filename(file.getName()).build());
        return new ResponseEntity<>(file.getFile().getBinary(), headers, HttpStatus.OK);
    }
    private boolean checkUserHasAccessToFile(Item file, String userEmail) {
        boolean isUserHasEditAccess = false;
        for(Permission permission:file.getPermissionGroup().getPermissionList()){
            if(permission.getUserEmail().equals(userEmail)) {
                isUserHasEditAccess = true;
                break;
            }
        }
        return isUserHasEditAccess;
    }
}

