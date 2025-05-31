# JavaJsonObfuscator

JavaJsonObfuscator is a tool designed to obfuscate/omit sensitive information from JSON files, making them less readable and more secure.

Given a list of sensitive keys, the tool will check for any keys in the JSON that contain these values (case-insensitive), and then remove or obfuscate these keys from the JSON output.

### Example input:

```java
String jsonString = "{\"name\":\"Mark\",\"contactDetails\":{\"streetAddress\":\"somewhere\",\"email\":\"mark@mveeprojects.com\"},\"creditCardNumber\":\"1234-5678-9012-3456\"}";
```

As Json this look like::
```json
{
  "name": "Mark",
  "contactDetails": {
    "streetAddress": "somewhere",
    "email": "mark@mveeprojects.com"
  },
  "creditCardNumber": "1234-5678-9012-3456"
}
```

### Example output:

When filtering by "email", "address", "creditcard" and setting `runMode = "OBFUSCATE"`.

```text
JSON after processing of sensitive fields: 
{"name":"Mark","contactDetails":{"streetAddress":"************","email":"************"},"creditCardNumber":"************"}
```

When filtering by "email", "address", "creditcard" and setting `runMode = "OMIT"`.

```text
JSON after processing of sensitive fields: 
{"name":"Mark","contactDetails":{}}
```

### Sources
* https://stackoverflow.com/questions/65400140/find-keys-in-json-with-regex-and-put-them-in-map-in-java
* https://www.baeldung.com/java-jsonnode-get-keys
* https://www.baeldung.com/jackson-object-mapper-tutorial
* https://www.baeldung.com/java-jackson-remove-json-elements
* https://www.baeldung.com/jackson-json-node-tree-model