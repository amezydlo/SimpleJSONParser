package org.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JSONParserTest {

    private static File testFile1;
    private static File testFile2;
    private static File testFile3;
    private static File testFile4;
    private static File testFile5;
    private static File testFile6;
    private static File testFile7;

    @BeforeAll
    public static void setUp() {
        testFile1 = new File("src/test/resources/test1.json");
        testFile2 = new File("src/test/resources/test2.json");
        testFile3 = new File("src/test/resources/test3.json");
        testFile4 = new File("src/test/resources/test4.json");
        testFile5 = new File("src/test/resources/test5.json");
        testFile6 = new File("src/test/resources/test6.json");
        testFile7 = new File("src/test/resources/test7.json");


    }
    @Test
    public void testSingleStatementWithNoSingleAsteriskResourceInArray() throws IOException {
        assertTrue(JSONParser.checkPolicy(testFile1));
    }

    @Test
    public void testStatementArrayWithSingleAsteriskResource() throws IOException {
        assertFalse(JSONParser.checkPolicy(testFile2));
    }

    @Test
    public void testStatementArrayWithMultipleAsteriskResources() throws IOException {
        assertFalse(JSONParser.checkPolicy(testFile3));
    }

    @Test
    public void testSingleStatementWithNoSingleAsteriskResource() throws IOException {
        assertTrue(JSONParser.checkPolicy(testFile4));
    }

    @Test
    public void testStatementArrayWithNoSingleAsteriskResourceInArray() throws IOException {
        assertTrue(JSONParser.checkPolicy(testFile5));
    }

    @Test
    public void testMissingResourceField() throws IOException {
        assertTrue(JSONParser.checkPolicy(testFile6));
    }

    @Test
    public void testMalformedJSON() {
        assertThrows(JSONParser.MalformedJSONException.class, () -> JSONParser.checkPolicy(testFile7));
    }
}