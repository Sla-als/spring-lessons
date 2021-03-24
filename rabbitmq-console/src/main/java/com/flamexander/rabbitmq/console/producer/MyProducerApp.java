package com.flamexander.rabbitmq.console.producer;


import com.rabbitmq.client.*;

import java.util.Scanner;

public class MyProducerApp {

    // Домашнее задание:
// 1. Сделайте два консольных приложения (не Спринг):
//
//   а. IT-блог, который публикует статьи по языкам программирования
//   б. Подписчик, которого интересуют статьи по определенным языкам
//
//   Детали a. Если IT-блог в консоли пишет 'php some message', то 'some message'
//   отправляется в RabbitMQ с темой 'php', и это сообщение получают подписчики
//   этой темы
//
//   Детали b. Подписчик при запуске должен ввести команду 'set_topic php', после
//   чего начнет получать сообщения из очереди с соответствующей темой 'php'
//
// 2. * Сделайте возможность клиентов подписываться и отписываться от статей по темам
// в процессе работы приложения-подписчика

    private static final String EXCHANGE_NAME = "it-blog";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            Scanner scanner = new Scanner(System.in);
            while (!scanner.hasNext("ext")) {

                String title = scanner.nextLine();
                String[] words = title.split("\\s", 2);
                String topic = "empty";
                String message = "empty";
                if (words.length == 2) {
                    topic = words[0];
                    message = words[1];
                }
                String infoMessage = "topic: " + "[" + topic + "]" + " message: " + "[" + message + "]";


//            channel.basicPublish(EXCHANGE_NAME, "programming.best-practices.java", null, message.getBytes("UTF-8"));
                channel.basicPublish(EXCHANGE_NAME, topic, null, message.getBytes("UTF-8"));
               // channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + infoMessage + "'");
            }
            scanner.close();
        }
    }

}
