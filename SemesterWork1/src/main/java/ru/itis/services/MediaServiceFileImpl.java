package ru.itis.services;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tika.mime.MimeTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class MediaServiceFileImpl implements MediaService {

    @Autowired
    private Path repositoryPath;

    public String saveFileItem(FileItem item){
        String fileName = RandomStringUtils.random(15, true, true);
        String extension;
        try {
            extension = MimeTypes.getDefaultMimeTypes().forName(item.getContentType()).getExtension();
            fileName += extension;
            item.write(repositoryPath.resolve(fileName).toFile());
        } catch (Exception e) {
            throw new IllegalArgumentException(e);
        }
        return fileName;
    }

    @Override
    public boolean fileExist(String path) {
        return repositoryPath.resolve(path).toFile().exists();
    }

    @Override
    public InputStream getFile(String path) {
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(repositoryPath.resolve(path).toFile());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
        return inputStream;
    }

    @Override
    public String getMimeType(String path) {
        try {
            return Files.probeContentType(repositoryPath.resolve(path));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
