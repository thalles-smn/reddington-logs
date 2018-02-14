package com.smn.restlog.queue;

import com.smn.restlog.exception.MongoLogOnMessageException;
import org.apache.activemq.command.ActiveMQObjectMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.MessageListener;

@Component
public class MongoLogListener implements MessageListener {

    @Autowired
    private MongoTemplate mongo;

    @Override
    public void onMessage(Message message) {

        try {

            if (message instanceof ActiveMQObjectMessage) {

                ActiveMQObjectMessage messageObj = (ActiveMQObjectMessage)message;

                if (messageObj.getObject() instanceof MongoLog) {
                    MongoLog mongoLog = (MongoLog) ((ActiveMQObjectMessage) message).getObject();
                    mongo.insert(mongoLog.getLog(), mongoLog.getCollection());
                }
                else if ( messageObj.getObject() instanceof MongoApiLog ){
                    MongoApiLog mongoApiLog = (MongoApiLog) ((ActiveMQObjectMessage) message).getObject();
                    mongo.insert(mongoApiLog.getLog(), mongoApiLog.getCollection());
                }
            }
        }
        catch ( Exception ex ){
            throw new MongoLogOnMessageException(ex, message);
        }
    }
}
