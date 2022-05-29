package cz.nss.onegram.post.service.impl;

import cz.nss.onegram.post.service.interfaces.CacheService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    @Override
    @Scheduled(fixedRate = 600 * 1000)
    public void evictUserCache() {
        log.warn("Clearing cache name: {}", "USER");
        cacheManager.getCache("USER").clear();
    }
}
