package org.mveeprojects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Application {

    static String runMode = "OBFUSCATE";
//    static String runMode = "OMIT";

    static ObjectMapper om = new ObjectMapper();

    static List<String> keywordsToObfuscate = List.of(
            "email",
            "address",
            "creditcard"
    );

    public static void main(String[] args) throws IOException {
        String jsonString = "{\"name\":\"Mark\",\"contactDetails\":{\"streetAddress\":\"somewhere\",\"email\":\"mark@mveeprojects.com\"},\"creditCardNumber\":\"1234-5678-9012-3456\"}";
        System.out.println("JSON after processing of sensitive fields: ");
        System.out.println(getAllKeysInJsonUsingJsonNodeFieldNames(jsonString, om));
    }

    public static JsonNode getAllKeysInJsonUsingJsonNodeFieldNames(String jsonString, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(jsonString);

        if (runMode.equals("OMIT")) {
            return removeFieldsFromJson(rootNode);
        } else {
            return obfuscateFieldsInJson(rootNode);
        }
    }

    static JsonNode removeFieldsFromJson(JsonNode jsonNode) {
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {

            Map.Entry<String, JsonNode> field = fields.next();

            if (field.getValue().isObject() || field.getValue().isArray()) {
                removeFieldsFromJson(field.getValue());
            } else {
                keywordsToObfuscate.forEach(keywordToObfuscate -> {
                    if (field.getKey().toLowerCase().contains(keywordToObfuscate.toLowerCase())) {
                        fields.remove();
                    }
                });
            }
        }
        return jsonNode;
    }

    static JsonNode obfuscateFieldsInJson(JsonNode jsonNode) {
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {

            Map.Entry<String, JsonNode> field = fields.next();

            if (field.getValue().isObject() || field.getValue().isArray()) {
                obfuscateFieldsInJson(field.getValue());
            } else {
                keywordsToObfuscate.forEach(keywordToObfuscate -> {
                    if (field.getKey().toLowerCase().contains(keywordToObfuscate.toLowerCase())) {
                        JsonNode obfuscatedNode = new TextNode("************");
                        field.setValue(obfuscatedNode);
                    }
                });
            }
        }
        return jsonNode;
    }
}
