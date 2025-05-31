# JavaJsonObfuscator

JavaJsonObfuscator is a tool designed to obfuscate/omit sensitive information from JSON files, making them less readable and more secure.

Given a list of sensitive keys, the tool will check for any keys in the JSON that contain these values (case-insensitive), and then remove these keys from the JSON output.

### Example input:

When filtering by "email", "address", "creditcard", see `keywordsToObfuscate` list in Application.java.

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
```text
JSON after removal of sensitive fields: 
{"name":"Mark","contactDetails":{}}
```

### Sources
* https://stackoverflow.com/questions/65400140/find-keys-in-json-with-regex-and-put-them-in-map-in-java
* https://www.baeldung.com/java-jsonnode-get-keys
* https://www.baeldung.com/jackson-object-mapper-tutorial
* https://www.baeldung.com/java-jackson-remove-json-elements
