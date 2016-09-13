package com.honeywell.ims.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LongSummaryStatistics;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@Component
public class JsonDateSerializer extends JsonDeserializer<Date> {

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");

	@Override
	public Date deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		String date = jsonParser.getText();
		try {
			return new Date(Long.valueOf(date)*1000);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}