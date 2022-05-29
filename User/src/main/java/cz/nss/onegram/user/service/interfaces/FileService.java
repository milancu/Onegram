package cz.nss.onegram.user.service.interfaces;

import java.io.InputStream;
import java.util.List;

public interface FileService {
    public String saveAsPngFile(InputStream file);

    public List<String> saveAsPngFiles(List<InputStream> files);

    public void deleteFile(String filename);

    public default String extractFilenameFromPath(String path){
        String[] splitPath =  path.split("/");
        return splitPath[splitPath.length - 1];
    }

    // TODO exists
}
