package com.cache.projectcache.outcall.multicast;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Component
@NoArgsConstructor
public class MulticastPublisherImpl implements MulticastPublisher {

    @Value("${multicast.group}")
    private String multicastGroup;

    @Value("${multicast.port}")
    private int multicastPort;

    @Value("${multicast.expected-servers}")
    private int expectedServerCount;

    private DatagramSocket socket;
    private byte[] buf;

    public void sendPacket(Object obj) throws IOException {
        this.socket = new DatagramSocket();
        InetAddress group = InetAddress.getByName(multicastGroup);

        try {buf = serializeObject(obj);
        DatagramPacket packet = new DatagramPacket(buf, buf.length, group, 4446);
        socket.send(packet);

        int receivedServersCount = receivePackets();
        if (expectedServerCount != receivedServersCount) {
            throw new RuntimeException("Expected " + expectedServerCount + " servers, but received " + receivedServersCount + " servers");
        }
        } finally {
            socket.close();
        }
    }

    private byte[] serializeObject(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private int receivePackets() throws IOException {
        int serversDiscovered = 0;
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        while (serversDiscovered != expectedServerCount) {
            socket.receive(packet);
            serversDiscovered++;
        }
        return serversDiscovered;
    }
}