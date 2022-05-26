package cz.nss.onegram.post.service.interfaces;

import org.springframework.beans.factory.annotation.Value;

public interface CacheService {
    public void evictUserCache();
}
