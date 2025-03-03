package com.grpcstudy.grpcstudy.services;

import io.grpc.stub.StreamObserver;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDateTime;
import static com.grpcstudy.grpcstudy.constant.Constant.SERVER_URL;

public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(
            HelloRequest request, StreamObserver<HelloResponse> responseStreamObserver){

        RestTemplate restTemplate = new RestTemplate();
        LocalDateTime dateTime = LocalDateTime.now();
        String requestResponse = "";
        StringBuilder finalResponse = new StringBuilder();

        try{
            requestResponse = restTemplate.getForObject(SERVER_URL, String.class);

            finalResponse.append("Hello ")
                    .append(request.getFirstName())
                    .append("  ")
                    .append(request.getLastName())
                    .append(requestResponse);

            HelloResponse response = HelloResponse.newBuilder()
                    .setGreeting(finalResponse.toString())
                    .build();

            responseStreamObserver.onNext(response);
            responseStreamObserver.onCompleted();

        }
        catch (final HttpClientErrorException e){

            if(HttpStatus.NOT_FOUND.equals(e.getStatusCode())){

                System.out.println("NOT FOUND");
            }
            else{
                System.out.println(e.getMessage());
            }

        }
    }

}
