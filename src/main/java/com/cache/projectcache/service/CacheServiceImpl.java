package com.cache.projectcache.service;

import com.cache.projectcache.cache.CacheSingleton;
import com.cache.projectcache.model.CacheMessage;
import org.springframework.stereotype.Service;

import javax.cache.Cache;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.List;

@Service
public class CacheServiceImpl implements ICacheService {

    private final Cache<String, Object> cache;
    private final InetAddress multicastAddress;
    private final MulticastSocket multicastSocket;


    public CacheServiceImpl() throws Exception {
        cache = CacheSingleton.getCache();
        multicastAddress = InetAddress.getByName("224.0.0.1");
        multicastSocket = new MulticastSocket();
    }

    @Override
    public String getCache(String key) {
        return (String) cache.get(key);
    }

    @Override
    public List<String> getAllCache() {
        List<String> list = new ArrayList<>();
        for(Cache.Entry<String, Object> entry : cache) {
            list.add((String) entry.getValue());
        }
        return list;
    }

    @Override
    public void deleteCache(String key) {
        //TODO: Implement delete cache
    }

    @Override
    public void updateCacheWithPushMultiCast(String key, String value) {
        updateCache(key, value);
        try {
            CacheMessage cacheMessage = new CacheMessage(key, value);
            byte[] messageData = serialize(cacheMessage);
            DatagramPacket packet = new DatagramPacket(messageData, messageData.length, multicastAddress, 4446);
            System.out.println("CacheServiceImpl.updateCacheWithPushMultiCast: " + cacheMessage);
            multicastSocket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCache(String key, String value) {
        System.out.println("CacheServiceImpl.updateCache: " + key + " " + value);
        cache.put(key, value);
    }

    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(obj);
        return baos.toByteArray();
    }
}
