package com.marios.gavriil.mazesolver.parsers

import org.springframework.util.ResourceUtils
import spock.lang.Specification

class CsvParserSpec extends Specification{

    private static final String CLASSPATH_FILES_MAZE_FOR_TEST_CSV = "classpath:files/mazeForTest.csv"
    private static final String CLASSPATH_FILES_MAZE_FOR_TEST_WRONG_CSV = "classpath:files/mazeForTestWrong.csv"

    CsvParser csvParser

    def setup() {
        csvParser = new CsvParser()
    }

    def "successful csv parsing" () {
        when:
        def array = csvParser.parse(ResourceUtils.getFile(CLASSPATH_FILES_MAZE_FOR_TEST_CSV).getPath())

        then:
        noExceptionThrown()
        array == createExpectedArray()
    }

    def "path is wrong" () {
        when:
        csvParser.parse(ResourceUtils.getFile(CLASSPATH_FILES_MAZE_FOR_TEST_WRONG_CSV).getPath())

        then:
        thrown(FileNotFoundException)
    }

    private static String[][] createExpectedArray() {
        String [][] expectedArray = new String[3][3]
        expectedArray[0][0] = "1"
        expectedArray[0][1] = "1"
        expectedArray[0][2] = "1"
        expectedArray[1][0] = "2"
        expectedArray[1][1] = "0"
        expectedArray[1][2] = "3"
        expectedArray[2][0] = "1"
        expectedArray[2][1] = "1"
        expectedArray[2][2] = "1"

        return expectedArray
    }
}
