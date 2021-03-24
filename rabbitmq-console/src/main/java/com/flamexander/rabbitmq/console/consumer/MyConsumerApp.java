package com.flamexander.rabbitmq.console.consumer;

import com.rabbitmq.client.*;

import java.util.Scanner;

public class MyConsumerApp {
    private static final String EXCHANGE_NAME = "it-blog";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);
        //channel.queueDeclare(EXCHANGE_NAME, true, false, false, null);
        channel.basicQos(2);


        Scanner scanner = new Scanner(System.in);
        String topic = scanner.nextLine();
        String[] words = topic.split("\\s", 2);
        String commandUser = words[0];
        String topicUser = words[1];
        scanner.close();

        String queueName = " ";
        if (commandUser.equals("set_topic")) {
            queueName = channel.queueDeclare().getQueue();
            System.out.println("My queue name: " + queueName);

            channel.queueBind(queueName, EXCHANGE_NAME, topicUser);
        }

        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
//            System.out.println(Thread.currentThread().getName());
            System.out.println(" [x] Received '" + message + "'");
            //channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

//        System.out.println(Thread.getAllStackTraces().keySet());
        channel.basicConsume(queueName, true, deliverCallback, consumerTag ->
        {
        });
//        Thread.sleep(3000);
//        System.out.println(Thread.getAllStackTraces().keySet());

        // channel.queueBind(queueName, EXCHANGE_NAME, "c++");
    }
}
