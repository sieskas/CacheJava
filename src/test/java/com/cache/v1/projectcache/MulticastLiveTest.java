//package com.cache.v1.projectcache;
//
//import com.cache.v2.cache.outcall.multicast.MulticastListener;
//import com.cache.v2.cache.outcall.multicast.MulticastClient;
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.Test;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//class MulticastLiveTest {
//    private MulticastClient client;
//
//    @Test
//    void whenBroadcasting_thenDiscoverExpectedServers() throws Exception {
//        int expectedServers = 4;
//        initializeForExpectedServers(expectedServers);
//
//        int serversDiscovered = client.discoverServers("hello server");
//        assertEquals(expectedServers, serversDiscovered);
//    }
//
//    private void initializeForExpectedServers(int expectedServers) throws Exception {
//        for (int i = 0; i < expectedServers; i++) {
//            new MulticastListener().start();
//        }
//
//        client = new MulticastClient(expectedServers);
//    }
//
//    @AfterEach
//    public void tearDown() throws IOException {
//        stopEchoServer();
//        client.close();
//    }
//
//    private void stopEchoServer() throws IOException {
//        client.discoverServers("end");
//    }
//}
//
