package codenotfound.grpcdemo.client;


import javax.annotation.PostConstruct;

import codenotfound.grpc.Greeting;
import codenotfound.grpc.HelloWorldServiceGrpc;
import codenotfound.grpc.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

@Component
public class HelloWorldClient {

    @Value("${server.addr}")
    private String serverAddr;

    private static final Logger LOGGER =
            LoggerFactory.getLogger(HelloWorldClient.class);

    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress(serverAddr, 6565).usePlaintext().build();

        helloWorldServiceBlockingStub =
                HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder().setFirstName(firstName)
                .setLastName(lastName).build();
        LOGGER.info("client sending {}", person);

        Greeting greeting =
                helloWorldServiceBlockingStub.sayHello(person);
        LOGGER.info("client received {}", greeting);

        return greeting.getMessage();
    }

}