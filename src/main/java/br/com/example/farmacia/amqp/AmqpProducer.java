package br.com.example.farmacia.amqp;

public interface AmqpProducer<T> {

	void producer(T t);
}
