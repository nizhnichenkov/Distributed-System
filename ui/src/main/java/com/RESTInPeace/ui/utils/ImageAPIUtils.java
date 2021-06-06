package com.RESTInPeace.ui.utils;

import com.RESTInPeace.models.ImageQueueWrapper;
import com.RESTInPeace.models.constants.ImageAPiConstants;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.jms.*;
import java.io.IOException;

@Log4j2
public class ImageAPIUtils {
    public static void sendPicture(@NonNull final MultipartFile file,
                                   @NonNull final String fileName) throws JMSException, IOException {
        final ImageQueueWrapper imageQueueWrapper = new ImageQueueWrapper(file.getBytes(), fileName);

        final ConnectionFactory factory = new ActiveMQConnectionFactory(ImageAPiConstants.QUEUE_ADDRESS);

        final Connection connection = factory.createConnection();

        connection.setClientID(String.format("ui-%s", fileName));

        final Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        final Queue requestsQueue = session.createQueue(ImageAPiConstants.QUEUE_NAME);
        final MessageProducer requestProducer = session.createProducer(requestsQueue);
        connection.start();

        final Message request = session.createObjectMessage(imageQueueWrapper);

        requestProducer.send(request);
    }
}
