package ru.itis.rest.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FilesServiceImpl implements FilesService {

//    @Resource(mappedName = "fileStorage/basePath")
//    private String basePath;
    private static String UPLOAD_LOCATION="C:/mytemp/";

    @Override
    public List<String> saveFiles(MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                //FileCopyUtils.copy(file.getBytes(), new File(/*UPLOAD_LOCATION*/ basePath + file.getOriginalFilename()));
                String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
                file.transferTo(new File(UPLOAD_LOCATION + filename));
                fileNames.add(filename);
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return fileNames;
    }

}
