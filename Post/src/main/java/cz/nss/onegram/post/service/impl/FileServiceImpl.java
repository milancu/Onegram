package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.exception.PostserviceException;
import cz.nss.onegram.post.service.interfaces.FileService;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Deprecated
public class FileServiceImpl implements FileService {
    @Override
    public String saveAsPngFile(InputStream inputStream) {
        ClassLoader classLoader = getClass().getClassLoader();
        String id = (new ObjectId()).toString();
        File targetFile = new File(classLoader.getResource(".").getFile() + "/img" + id + ".png");
        log.info("Starting to write file to {}", targetFile.getPath());

        try {
            int read;
            targetFile.createNewFile();
            OutputStream outStream = new FileOutputStream(targetFile);
            while ((read = inputStream.read()) != -1){
                outStream.write(read);
            }
            log.info("File written successfully.");
            return targetFile.getPath();
        } catch (IOException e) {
            throw new PostserviceException("Target file was not created");
        }
    }

    @Override
    public List<String> saveAsPngFiles(List<InputStream> files) {
        List<String> result = new ArrayList<>();
        files.forEach(f -> result.add(saveAsPngFile(f)));
        return result;
    }

    @Override
    public void deleteFile(String filename) {
        throw new UnsupportedOperationException();
    }
}
