package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.model.SentimentData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SentimentConsumerService {
    @Autowired
    private EmailService emailService;
    @KafkaListener(topics = "week-sentiments", groupId = "week-sentiment-group")
    public void consume(SentimentData sentimentData){
        sendEmail(sentimentData);
    }
    public void sendEmail(SentimentData sentimentData){
        emailService.sendMail(sentimentData.getEmail(), "Sentiment for last 7 days", sentimentData.getSentiment());
    }
}
