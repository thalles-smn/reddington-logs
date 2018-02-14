package com.smn.restlog.queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Queue;

@Component
public class MongoLogSender {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Queue queueMongoLog;

    public void send(MongoLog log) {
        this.jmsTemplate.convertAndSend(this.queueMongoLog, log);
    }

}
