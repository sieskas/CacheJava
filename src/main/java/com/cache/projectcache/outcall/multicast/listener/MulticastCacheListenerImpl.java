package com.cache.projectcache.outcall.multicast.listener;

import com.cache.projectcache.service.CacheService;
import com.cache.projectcache.domain.model.Cache;

import java.io.*;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class MulticastCacheListenerImpl extends Thread {

    private final CacheService cacheService;

    private String multicastGroup;
    private int multicastPort;
    protected MulticastSocket socket;
    protected byte[] buf = new byte[256];
    protected InetAddress group;

    public MulticastCacheListenerImpl(CacheService cacheService, String multicastGroup, int multicastPort) throws IOException {
        this.cacheService = cacheService;
        this.multicastGroup = multicastGroup;
        this.multicastPort = multicastPort;
        socket = new MulticastSocket(this.multicastPort);
        socket.setReuseAddress(true);
        group = InetAddress.getByName(this.multicastGroup);
        socket.joinGroup(group);
    }

    public void run() {
        try {
            while (true) {
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);
                byte[] data = packet.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream ois = new ObjectInputStream(in);
                Object obj = ois.readObject();
                if (obj instanceof Cache) {
                    cacheService.updateCache((Cache) obj);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}