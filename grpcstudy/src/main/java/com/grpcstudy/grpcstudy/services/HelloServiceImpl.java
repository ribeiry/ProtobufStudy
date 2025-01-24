package com.grpcstudy.grpcstudy.services;

import io.grpc.stub.StreamObserver;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseStreamObserver){


        String greetings = new StringBuilder()
                .append("Hello, ")
                .append(request.getFirstName())
                .append("  ")
                .append(request.getLastName())
                .append("  ")
                .append("Response for server")
                .toString();

        HelloResponse response = HelloResponse.newBuilder()
                .setGreeting(greetings)
                .build();

        responseStreamObserver.onNext(response);
        responseStreamObserver.onCompleted();
    }

}
