package test.eliteixeira.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {


    private final String topicName;
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	
	
	public KafkaProducer(@Value("${topicName}") String topicName, KafkaTemplate<String, String> kafkaTemplate) {
		this.topicName = topicName;
		this.kafkaTemplate = kafkaTemplate;
	}



	public void send(String message){
        kafkaTemplate.send(topicName, message);
    }
}
