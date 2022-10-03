package com.example.command_web_service;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String... args) {
        Endpoint.publish("http://localhost:8081/wss/first", new CommandWsImpl());
    }
}
