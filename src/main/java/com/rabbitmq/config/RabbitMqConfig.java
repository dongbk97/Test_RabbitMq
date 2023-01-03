package com.rabbitmq.config;


import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.BindingBuilder;
@Configuration
public class RabbitMqConfig {


    private String host ="localhost";
    private int port=5672;
    private String user="guest";
    private String password="guest";


    @Bean
    public ConnectionFactory connectionFactory() {

        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(user);
        connectionFactory.setPassword(password);


        return connectionFactory;

    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {

        RabbitTemplate rabbitTemplate= new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        return rabbitTemplate;
    }
    @Bean
    public Queue queue(){
        return  new Queue("Queue_1");
    }
    @Bean
    public Exchange exchange(){
        Exchange exchange= new DirectExchange("exchange-1");
        return exchange;
    }


    @Bean
    public Binding binding(Queue queue, Exchange exchange){

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("routingKey")
                .noargs();
    }

}
