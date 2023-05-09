package org.grpc.sample.service;

import io.grpc.stub.StreamObserver;
import org.grpc.sample.server.GuessNumberGrpc;
import org.grpc.sample.server.GuessRequest;
import org.grpc.sample.server.HowLuckyResponse;

import java.security.SecureRandom;

public class GuessNumberServiceImpl extends GuessNumberGrpc.GuessNumberImplBase {

    private static final String LUCKY_MESSAGE = "WOW, you are very lucky today for your guessed number. Have a seat we tell you what you've won .....";
    private static final String UNLUCKY_MESSAGE = "HAHA, you are NOT lucky today for your guessed number. Try again next time you might be lucky";

    @Override
    public void letUsSeeHowLuckyYouMayBe(GuessRequest request, StreamObserver<HowLuckyResponse> responseObserver) {

        var userNumber = request.getGuessedNumber();
        HowLuckyResponse response = HowLuckyResponse.newBuilder().setIsLucky(false).setMessage(UNLUCKY_MESSAGE).build();
        if (isUserLucky(userNumber)){
             response = HowLuckyResponse.newBuilder().setIsLucky(true).setMessage(LUCKY_MESSAGE).build();
        }

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private boolean isUserLucky(long userNumber){
        var random = new SecureRandom().nextLong(userNumber);
        return random % 2 == 0 || random % 5 == 0;
    }
}
