package br.com.example.farmacia.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration // Indica que a classe terá um ou mais @Bean e pode gerar definições de bean e solicitações de serviço para os beans
@PropertySource(value = {"classpath:application.yml"})
public class ProducerRabbitConfiguration {

	@Value("${spring.rabbitmq.request.routing-key.producer}")
	private String queue;
	@Value("${spring.rabbitmq.request.exchange.producer}")
	private String exchange;
	@Value("${spring.rabbitmq.request.dead-letter.producer}")
	private String deadLetter;

	@Bean
	DirectExchange exchange() { // Encaminha mensagens que possuam exatamente a mesma rota das queues associada a esta exchange
		return new DirectExchange(exchange);
	}

	@Bean
	Queue queue() {
		return QueueBuilder.durable(queue).deadLetterExchange(exchange).deadLetterRoutingKey(deadLetter).build();
		// Cria um construtor para uma fila durável, dizendo para onde rotear as
		// mensagens expiradas ou rejeitadas definindo a chave de roteamento a ser usada
		// para rotear as mensagens expiradas ou rejeitadas e no final contruindo uma
		// fila final.
	}

	@Bean
	Queue deadLetter() {
		return QueueBuilder.durable(deadLetter).deadLetterExchange(exchange).deadLetterRoutingKey(queue).build();
	}

	@Bean 
	public Binding bindingQueue() { // é um relacionamento entre uma exchange e uma queue e pode ser lida como a
									// queue está interessada nas mensagens dessa exchange
		return BindingBuilder.bind(queue()).to(exchange()).with(queue); // Passamos a exchange e a queue para o
																		// BindingBuilder e ligamos a queue a exchange
																		// com uma chave de ligação que é o Value.
	}

	@Bean
	public Binding bindingDeadLetter() {
		return BindingBuilder.bind(deadLetter()).to(exchange()).with(deadLetter);
		// Passamos a exchange e a queue para o
		// BindingBuilder e ligamos a deadLetter a exchange
		// com uma chave de ligação que é o Value.
	}

}
