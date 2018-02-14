package com.smn.restlog;

import com.smn.restlog.queue.MongoLogListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import javax.jms.Queue;

@Configuration
@EnableJms
public class ActiveMQConfig {

    private static final String QUEUE_NAME = "mongolog";

    @Autowired
    private MongoLogListener mongoLogListener;

    @Bean
    public Queue queueMongoLog(){
        return new ActiveMQQueue(QUEUE_NAME);
    }

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory(){

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://embedded?broker.persistent=false,useShutdownHook=false");
        factory.setTrustAllPackages(true);

        return factory;
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.TEXT);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

    @Bean
    public MessageListenerContainer listenerContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(activeMQConnectionFactory());
        container.setDestinationName(QUEUE_NAME);
        container.setMessageListener(mongoLogListener);
        return container;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setDefaultDestination(queueMongoLog());
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }

}
