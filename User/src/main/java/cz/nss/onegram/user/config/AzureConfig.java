package cz.nss.onegram.user.config;

import com.azure.storage.blob.BlobClientBuilder;
import com.azure.storage.blob.BlobContainerClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureConfig {

    @Value("${spring.cloud.azure.storage.blob.connection-string}")
    private String connectionString;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Bean
    public BlobContainerClient getClient() {
        BlobClientBuilder client = new BlobClientBuilder();
        client.connectionString(connectionString);
        client.containerName(containerName);
        client.blobName("TODO"); // TODO is required, however the value gets changed when actually working with the blob in service
        return client.buildClient().getContainerClient();
    }
}
