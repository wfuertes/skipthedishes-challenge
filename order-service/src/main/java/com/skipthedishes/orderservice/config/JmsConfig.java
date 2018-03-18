package com.skipthedishes.orderservice.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.skipthedishes.orderservice.service.JsonMessageConverter;

@EnableJms
@Configuration
public class JmsConfig {

	@Autowired
	private JsonMessageConverter messageConverter;

	@Bean
	public CachingConnectionFactory getCachingConnectionFactory() {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
		connectionFactory.setBrokerURL("tcp://localhost:61616");

		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(connectionFactory);
		cachingConnectionFactory.setSessionCacheSize(10);
		return cachingConnectionFactory;
	}

	@Bean
	public ActiveMQQueue getDefaultDestination() {
		ActiveMQQueue queue = new ActiveMQQueue("IN.ORDERS");
		return queue;
	}

	@Bean
	public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
		DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
		factory.setConnectionFactory(getCachingConnectionFactory());
		return factory;
	}

	@Bean
	public JmsTemplate getJmsTemplate() {
		JmsTemplate template = new JmsTemplate(getCachingConnectionFactory());
		template.setDefaultDestination(getDefaultDestination());
		template.setMessageConverter(messageConverter);
		return template;
	}

	@Bean
	public ObjectMapper getMapper() {
		return new ObjectMapper();
	}
}
