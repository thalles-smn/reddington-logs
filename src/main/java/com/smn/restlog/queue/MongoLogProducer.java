package com.smn.restlog.queue;

import com.smn.restlog.dto.ApiLogDTO;
import com.smn.restlog.dto.BasicLogDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import java.util.Date;

@Service
public class MongoLogProducer {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void produce(final String collection, final BasicLogDTO log){

        jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {

                log.setDateCreated(new Date());
                MongoLog mongoLog = new MongoLog(collection, log);

                ObjectMessage message = session.createObjectMessage();
                message.setObject(mongoLog);

                return message;

            }
        });

    }

    public void produce(final String collection, final ApiLogDTO log){

        jmsTemplate.send(new MessageCreator() {

            @Override
            public Message createMessage(Session session) throws JMSException {

                log.setDateCreated(new Date());
                MongoApiLog mongoApiLog = new MongoApiLog(collection, log);

                ObjectMessage message = session.createObjectMessage();
                message.setObject(mongoApiLog);

                return message;
            }
        });
    }

}
