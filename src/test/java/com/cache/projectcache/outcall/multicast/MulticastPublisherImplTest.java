package com.cache.projectcache.outcall.multicast;

import com.cache.projectcache.domain.model.Cache;
import com.cache.projectcache.outcall.multicast.listener.MulticastCacheListenerImpl;
import com.cache.projectcache.service.CacheService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MulticastTest {

    private String multicastGroup = "230.0.0.0";

    private int multicastPort = 4446;

    @Mock
    private CacheService cacheService;

    @Test
    void testerMultiCast() throws IOException {
        Cache cacheToSend = new Cache("a", "test");

        MulticastCacheListenerImpl listener = new MulticastCacheListenerImpl(cacheService, multicastGroup, multicastPort);
        listener.start();
        MulticastPublisher multicastPublisher = new MulticastPublisherImpl();
        ReflectionTestUtils.setField(multicastPublisher, "multicastGroup", multicastGroup);
        ReflectionTestUtils.setField(multicastPublisher, "multicastPort", multicastPort);
        multicastPublisher.sendPacket(cacheToSend);
        verify(cacheService).updateCache(cacheToSend);
        listener.interrupt();
    }


}