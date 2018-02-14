package com.smn.restlog.queue;

import com.smn.restlog.exception.MongoLogOnMessageException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.Message;
import javax.jms.MessageListener;

public class QueueListener implements MessageListener {

    @Autowired
    private MongoLogSender sender;

    @Override
    public void onMessage(Message message) {

        if ( message instanceof ActiveMQObjectMessage) {

            try {

                MongoLog mongoLog = (MongoLog) ((ActiveMQObjectMessage) message).getObject();
                sender.send(mongoLog);
            }
            catch ( Exception ex ) {
                throw new MongoLogOnMessageException(message);
            }
        }
    }
}
