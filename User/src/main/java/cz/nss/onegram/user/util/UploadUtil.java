package cz.nss.onegram.user.util;

import graphql.kickstart.servlet.context.DefaultGraphQLServletContext;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class UploadUtil {
    public static List<InputStream> extractFiles(DataFetchingEnvironment environment) throws IOException {
        DefaultGraphQLServletContext context = environment.getContext();
        List<Part> parts = context.getFileParts();
        List<InputStream> streams = new ArrayList<>();

        log.info("Extracting {} uploaded files.", parts.size());

        for (Part p : parts){
            log.info("Uploaded file size: {} B", p.getSize());
            streams.add(p.getInputStream());
        }

        return streams;
    }

    public static void validateUploadedImages(DataFetchingEnvironment environment){
        DefaultGraphQLServletContext context = environment.getContext();
        List<Part> parts = context.getFileParts();

        parts.forEach(f -> {
            if (!f.getContentType().equals("image/png")){
                throw new RuntimeException("The uplaoded files must be.png.");
            }
        });

        log.info("Uploaded image validation successful.");
    }
}
