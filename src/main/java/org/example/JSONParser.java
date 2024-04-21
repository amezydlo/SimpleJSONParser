package org.example;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JSONParser {

    static class MalformedJSONException extends RuntimeException {
        MalformedJSONException(String message) {
            super(message);
        }
    }

    public static boolean checkPolicy(File policyFile) throws IOException {
        JsonNode statementNode = getJsonNode(policyFile);

        boolean isValidResource = false;
        if (statementNode.isArray()) {
            for (JsonNode statement : statementNode) {
                isValidResource = checkResource(statement);
                if (!isValidResource)
                    break;
            }
        } else
            isValidResource = checkResource(statementNode);

        return isValidResource;
    }

    private static JsonNode getJsonNode(File policyFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(policyFile);

        if (!rootNode.has("PolicyDocument"))
            throw new MalformedJSONException("JSON file missing required field 'PolicyDocument'.");
        JsonNode policyDocument = rootNode.get("PolicyDocument");

        if (!policyDocument.has("Statement"))
            throw new MalformedJSONException("JSON file missing required field 'Statement'");
        return policyDocument.get("Statement");
    }

    private static boolean checkResource(JsonNode statement) {
        if (statement.has("Resource")) {
            JsonNode resourceNode = statement.get("Resource");

            if (resourceNode.isArray()) {
                for (JsonNode resource : resourceNode) {
                    if (resource.asText().equals("*"))
                        return false;
                }
            } else {
                return !resourceNode.asText().equals("*");
            }
        }

        return true;
    }
}
