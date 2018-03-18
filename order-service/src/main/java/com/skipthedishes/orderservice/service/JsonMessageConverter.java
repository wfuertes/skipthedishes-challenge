package com.skipthedishes.orderservice.service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonMessageConverter implements MessageConverter {

	private ObjectMapper mapper;

	@Autowired
	public JsonMessageConverter(final ObjectMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
		try {
			String text = mapper.writeValueAsString(object);
			TextMessage message = session.createTextMessage(text);
			message.setJMSType(object.getClass().getCanonicalName());
			return message;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object fromMessage(Message message) throws JMSException, MessageConversionException {
		try {
			TextMessage textMessage = (TextMessage) message;
			Class<?> type = Class.forName(message.getJMSType());
			Object object = mapper.readValue(textMessage.getText(), type);
			return object;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
