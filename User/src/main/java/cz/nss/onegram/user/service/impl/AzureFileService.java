package cz.nss.onegram.user.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import cz.nss.onegram.user.service.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Primary
@RequiredArgsConstructor
public class AzureFileService implements FileService {

    private final BlobContainerClient containerCLient;

    @Override
    public String saveAsPngFile(InputStream file) {
        try {
            BlobClient client = containerCLient.getBlobClient(new ObjectId() + ".png");
            client.upload(file, file.available());
            return client.getBlobUrl();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image could not be saved.");
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
        BlobClient client = containerCLient.getBlobClient(filename);
        client.delete();
        log.info("Deleted file from Azure Storage: " + filename);
    }
}
