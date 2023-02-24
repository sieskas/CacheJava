package com.cache.projectcache.listener;

import com.cache.projectcache.cache.CacheSingleton;
import com.cache.projectcache.model.CacheMessage;
import com.cache.projectcache.service.CacheServiceImpl;
import com.cache.projectcache.service.ICacheService;
import org.springframework.stereotype.Component;

import javax.cache.Cache;
import java.io.ByteArrayInputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CacheUpdateListener implements Runnable {

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final InetAddress multicastAddress;
    private final MulticastSocket multicastSocket;
    private final ICacheService cacheService;

    public CacheUpdateListener(ICacheService cacheService) throws Exception {
        this.cacheService = cacheService;
        multicastAddress = InetAddress.getByName("224.0.0.1");
        multicastSocket = new MulticastSocket(4446);
        multicastSocket.joinGroup(multicastAddress);
        start();
    }

    @Override
    public void run() {
        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        while (!Thread.currentThread().isInterrupted()) {
            try {
                multicastSocket.receive(packet);
                CacheMessage cacheMessage = deserialize(packet.getData());
                System.out.println("CacheUpdateListener.run: " + cacheMessage);
                cacheService.updateCache(cacheMessage.getKey(), cacheMessage.getValue());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void start() {
        executorService.submit(this);
    }

    public void stop() {
        executorService.shutdownNow();
        multicastSocket.close();
    }

    private CacheMessage deserialize(byte[] data) throws Exception {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(data);
             ObjectInput in = new ObjectInputStream(bis)) {
            return (CacheMessage) in.readObject();
        }
    }
}
