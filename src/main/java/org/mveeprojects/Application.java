package org.mveeprojects;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.TextNode;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Application {

    static String jsonFileName = "libraryapi.json";

        static String runMode = "OBFUSCATE";
//    static String runMode = "OMIT";

    static ObjectMapper om = new ObjectMapper();

    static List<String> keywordsToObfuscateOrOmit = List.of(
            "cardNumber",
            "expiryDate",
            "email",
            "street",
            "city",
            "postalCode"
    );

    public static void main(String[] args) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("./src/main/resources/" + jsonFileName)));
        System.out.println("JSON after processing of sensitive fields: ");
        System.out.println(obfuscateOrOmitSensitiveFields(jsonString, om).toPrettyString());
    }

    public static JsonNode obfuscateOrOmitSensitiveFields(String jsonString, ObjectMapper mapper) throws JsonProcessingException {
        JsonNode rootNode = mapper.readTree(jsonString);
        return obfuscateOrOmit(rootNode);
    }

    static JsonNode obfuscateOrOmit(JsonNode jsonNode) {
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNode.fields();
        while (fields.hasNext()) {

            Map.Entry<String, JsonNode> field = fields.next();

            keywordsToObfuscateOrOmit.forEach(keywordToOmit -> {
                if (field.getValue().isObject()) {
                    obfuscateOrOmit(field.getValue());
                } else if (field.getValue().isArray()) {
                    for (JsonNode arrayElement : field.getValue()) {
                        obfuscateOrOmit(arrayElement);
                    }
                } else if (field.getKey().toLowerCase().contains(keywordToOmit.toLowerCase())) {
                    if (runMode.equals("OMIT")) {
                        fields.remove();
                    } else {
                        JsonNode obfuscatedNode = new TextNode("************");
                        field.setValue(obfuscatedNode);
                    }
                }
            });
        }
        return jsonNode;
    }
}
