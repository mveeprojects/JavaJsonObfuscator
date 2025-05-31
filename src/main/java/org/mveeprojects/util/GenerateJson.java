package org.mveeprojects.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

// Utility class to generate a JSON file from a Java object using Jackson.
// This can be used to quickly alter the JSON String used in the Application class.

public class GenerateJson {

    public static void main(String[] args) throws IOException {
        ObjectMapper om = new ObjectMapper();
        Person person = new Person("Mark", new ContactDetails("somewhere", "mark@mveeprojects.com"), "1234-5678-9012-3456");
        writeObjectToFile(om, person, "person.json");
    }

    public static void writeObjectToFile(ObjectMapper om, Object object, String filePath) throws IOException {
        om.writeValue(new File(filePath), object);
    }

    record Person(String name, ContactDetails contactDetails, String creditCardNumber) {
    }

    record ContactDetails(String streetAddress, String email) {
    }
}
