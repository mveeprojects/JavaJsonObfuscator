# JavaJsonObfuscator

JavaJsonObfuscator is a tool designed to obfuscate/omit sensitive information from JSON files, making them less readable and more secure.

Given a list of sensitive keys, the tool will check for any keys in the JSON that contain these values (case-insensitive), and then remove or obfuscate these keys from the JSON output.

### Example operation

This example by default reads libraryapi.json from the resources folder, but you can also provide your own JSON file.

When filtering by "cardNumber", "expiryDate", "email", "street", "city", "postalCode" and setting `runMode = "OBFUSCATE"`.

```text
JSON after processing of sensitive fields: 
{
  "customerDetails" : {
    "customerId" : "C12345",
    "name" : "Jane Doe",
    "email" : "************",
    "address" : {
      "street" : "************",
      "city" : "************",
      "postalCode" : "************"
    }
  },
  "paymentDetails" : {
    "cardType" : "Visa",
    "cardNumber" : "************",
    "expiryDate" : "************"
  },
  "alternativePaymentDetails" : [ {
    "cardType" : "MasterCard",
    "cardNumber" : "************",
    "expiryDate" : "************"
  }, {
    "cardType" : "American Express",
    "cardNumber" : "************",
    "expiryDate" : "************"
  } ],
  "rentalHistory" : [ {
    "bookId" : "B001",
    "title" : "Effective Java",
    "rentedOn" : "2024-05-01",
    "returnedOn" : "2024-05-15"
  }, {
    "bookId" : "B002",
    "title" : "Clean Code",
    "rentedOn" : "2024-06-01",
    "returnedOn" : null
  } ]
}
```

When filtering by "cardNumber", "expiryDate", "email", "street", "city", "postalCode" and setting `runMode = "OMIT"`.

```text
JSON after processing of sensitive fields: 
{
  "customerDetails" : {
    "customerId" : "C12345",
    "name" : "Jane Doe",
    "address" : { }
  },
  "paymentDetails" : {
    "cardType" : "Visa"
  },
  "alternativePaymentDetails" : [ {
    "cardType" : "MasterCard"
  }, {
    "cardType" : "American Express"
  } ],
  "rentalHistory" : [ {
    "bookId" : "B001",
    "title" : "Effective Java",
    "rentedOn" : "2024-05-01",
    "returnedOn" : "2024-05-15"
  }, {
    "bookId" : "B002",
    "title" : "Clean Code",
    "rentedOn" : "2024-06-01",
    "returnedOn" : null
  } ]
}
```

### Sources
* https://stackoverflow.com/questions/65400140/find-keys-in-json-with-regex-and-put-them-in-map-in-java
* https://www.baeldung.com/java-jsonnode-get-keys
* https://www.baeldung.com/jackson-object-mapper-tutorial
* https://www.baeldung.com/java-jackson-remove-json-elements
* https://www.baeldung.com/jackson-json-node-tree-model