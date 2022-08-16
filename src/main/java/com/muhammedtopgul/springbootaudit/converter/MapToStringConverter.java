package com.muhammedtopgul.springbootaudit.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author muhammed-topgul
 * @since 16/08/2022 14:04
 */
@Converter
public class MapToStringConverter implements AttributeConverter<Map<String, Map<String, String>>, String> {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(Map<String, Map<String, String>> data) {
        String value = "";
        try {
            value = mapper.writeValueAsString(data);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Override
    public Map<String, Map<String, String>> convertToEntityAttribute(String data) {
        Map<String, Map<String, String>> mapValue = new HashMap<>();
        TypeReference<HashMap<String, Map<String, String>>> typeRef = new TypeReference<>() {};
        try {
            mapValue = mapper.readValue(data, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapValue;
    }

}
