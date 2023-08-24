package org.crypto.training.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MessageService {
    @Autowired
    private AmazonSQS sqs;

    private String quequeName="test_project";

    public void sendMessage(String messageBody, int delayseconds) {
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(getQueueUrl(quequeName))
                .withMessageBody(messageBody)
                .withDelaySeconds(delayseconds);
        sqs.sendMessage(send_msg_request);
    }

    private String getQueueUrl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queueName);
        return getQueueUrlResult.getQueueUrl();
    }
}
