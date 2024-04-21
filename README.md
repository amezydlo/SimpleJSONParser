# JSON Parser

## Description

JSON Parser is a simple Java program that parses JSON files to check if the "Resource" field contains a single asterisk "*".


## Methods

### `checkPolicy(File policyFile): boolean`

The `checkPolicy` method takes a file representing a policy in JSON format and returns `true` if the "Resource" field does not contain a single asterisk "*", otherwise returns `false`.

#### Parameters

- `policyFile`: A file representing a policy in JSON format.

#### Exceptions

- `IOException`: Thrown if there is an issue with reading the file.

### `getJsonNode(File policyFile): JsonNode`

The `getJsonNode` method reads a JSON file and returns a JsonNode representing the "Statement" field of the policy.

#### Parameters

- `policyFile`: A file representing a policy in JSON format.

#### Exceptions

- `IOException`: Thrown if there is an issue with reading the file.
- `MalformedJSONException`: Thrown if the JSON file is malformed, i.e., it lacks required fields.

### `checkResource(JsonNode statement): boolean`

The `checkResource` method checks if the "Resource" field in a given JsonNode object contains a single asterisk "*".

#### Parameters

- `statement`: A JsonNode object representing the "Statement" field.

## Example Usage

```java
File policyFile = new File("path/to/policy.json");
boolean isValid = JSONParser.checkPolicy(policyFile);
System.out.println("Policy is valid: " + isValid);
```

## Dependencies

- `Jackson`

#### Using `Jackson` with gradle
```groovy
implementation group: 'com.fasterxml.jackson.core', name: 'jackson-databind', version: '2.17.0'
```



## How to run

`./gradlew run --args "<filename1> <filename2> ... <filenameN>"`

Assuming that application is run in the same directory as test1.json and test4.json are located in.


#### Sample Input
`./gradlew run --args "test1.json test.4.json"`

#### Sample Output
```text
test1.json: true
test4.json: true
```
