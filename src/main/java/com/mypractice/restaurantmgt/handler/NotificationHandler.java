package com.mypractice.restaurantmgt.handler;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Component
public class NotificationHandler extends TextWebSocketHandler {
    private static Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws IOException {
        // Handle incoming messages, if needed
        System.out.println(message.getPayload());
    }

    public void   sendNotification(String notification) throws IOException {
        TextMessage message = new TextMessage(notification);
        for (WebSocketSession session : sessions) {
            session.sendMessage(message);
        }
    }

}
