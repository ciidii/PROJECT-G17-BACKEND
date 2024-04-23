package com.projet17backend.backend.services;

import com.projet17backend.backend.entities.Article;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FileStorageService {
    public List<String> saveFile(List<MultipartFile> files, Long userId);
}
