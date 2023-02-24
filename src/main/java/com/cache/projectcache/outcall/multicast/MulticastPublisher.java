package com.cache.projectcache.outcall.multicast;

import java.io.IOException;

public interface MulticastPublisher {

    void sendPacket(Object obj) throws IOException;
}
