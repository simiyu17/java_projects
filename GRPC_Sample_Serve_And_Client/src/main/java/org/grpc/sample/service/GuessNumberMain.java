package org.grpc.sample.service;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GuessNumberMain {

    private static final int PORT = 50051;
    private Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(PORT)
                .addService(new GuessNumberServiceImpl())
                .build()
                .start();
    }

    public void blockUntilShutdown() throws InterruptedException {
        if (server == null) {
            return;
        }

        server.awaitTermination();
    }

    public static void main(String[] args)
            throws InterruptedException, IOException {
        GuessNumberMain server = new GuessNumberMain();
        System.out.println("Starting server ....... ");
        server.start();
        System.out.println("Startd server on port: "+PORT);
        server.blockUntilShutdown();
    }
}
