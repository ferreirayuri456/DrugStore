package br.com.example.farmacia.amqp.implementationAmqp;

import org.springframework.amqp.AmqpRejectAndDontRequeueException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.example.farmacia.amqp.AmqpProducer;
import br.com.example.farmacia.model.dto.ProductDTO;

@Component
public class ProducerRabbitMQ implements AmqpProducer<ProductDTO> {

	@Autowired
	private RabbitTemplate rabbit;

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;

	@Override
	public void producer(ProductDTO t) {
		try {
			rabbit.convertAndSend(exchange, queue, t);
		} catch (Exception e) {
			throw new AmqpRejectAndDontRequeueException(e);
		}
	}


}
