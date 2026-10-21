package org.grpc.sample.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.grpc.sample.server.GuessNumberGrpc;
import org.grpc.sample.server.GuessRequest;
import org.grpc.sample.server.HowLuckyResponse;

public class GuessNumberClient {

    private static final int PORT = 50051;

    private HowLuckyResponse testHowLuckyAreYou(long guestNumber){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", PORT).usePlaintext().build();
        GuessNumberGrpc.GuessNumberBlockingStub luckyStub = GuessNumberGrpc.newBlockingStub(channel);
        return luckyStub.letUsSeeHowLuckyYouMayBe(GuessRequest.newBuilder().setGuessedNumber(guestNumber).build());
    }

    public static void main(String[] args) {

        GuessNumberClient client = new GuessNumberClient();
        HowLuckyResponse response = client.testHowLuckyAreYou(100L);
        System.out.println(response.toString());
    }
}
