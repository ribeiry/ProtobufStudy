package com.grpcstudy.grpcstudy.client;

import com.grpcstudy.grpcstudy.services.HelloRequest;
import com.grpcstudy.grpcstudy.services.HelloResponse;
import com.grpcstudy.grpcstudy.services.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {

    public static void main(String[] args){
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost",8080)
                .usePlaintext()
                .build();

        HelloServiceGrpc.HelloServiceBlockingStub stub =
                HelloServiceGrpc.newBlockingStub(channel);

        HelloResponse helloResponse = stub.hello(HelloRequest.newBuilder()
                .setFirstName("Augusto")
                .setLastName("grpc")
                .build());

        System.out.println(helloResponse.getGreeting());
        channel.shutdown();
    }
}
