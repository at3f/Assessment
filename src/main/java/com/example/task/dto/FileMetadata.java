package com.example.task.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileMetadata {
    private Long fileId;
    private String fileName;
    private byte[] bytes;
}

