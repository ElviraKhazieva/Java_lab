package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesService {

    List<String> saveFiles(MultipartFile [] files);

}
