package com.mechanitis.demo.sense.twitter.server;

import com.mechanitis.demo.sense.message.MessageBroadcaster;
import com.mechanitis.demo.sense.message.MessageListener;
import com.mechanitis.demo.sense.sockets.WebSocketServer;

public class TweetsServer implements Runnable {
    private static final int PORT = 8081;
    private WebSocketServer server;
    private final MessageBroadcaster<String> tweetsEndpoint;

    public TweetsServer() {
        tweetsEndpoint = new MessageBroadcaster<>();
    }

    public static void main(String[] args) {
        new TweetsServer().run();
    }

    @Override
    public void run() {
        server = new WebSocketServer(PORT, "/tweets/", tweetsEndpoint);
        server.run();
    }

    public void stop() throws Exception {
        server.stop();
    }

    public MessageListener<String> getMessageListener() {
        return tweetsEndpoint;
    }
}
