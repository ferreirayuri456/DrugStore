package br.com.example.farmacia.amqp.implementationAmqp;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.example.farmacia.amqp.AmqpProducer;
import br.com.example.farmacia.model.dto.ManufacturerDTO;

@Component
public class ProducerManufactureRabbitMQ implements AmqpProducer<ManufacturerDTO> {
	
	@Autowired
	private RabbitTemplate rabbit;
	
	@Value("${spring.rabbitmq.request.routing-key.producer-manufacture}")
	private String queue;

	@Value("${spring.rabbitmq.request.exchange.producer-manufacture}")
	private String exchange;

	@Override
	public void producer(ManufacturerDTO dto) {
		try {
			rabbit.convertAndSend(exchange, queue, dto);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(e.getMessage());
		}
	}

}
