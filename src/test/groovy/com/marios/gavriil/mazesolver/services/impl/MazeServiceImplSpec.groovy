package com.marios.gavriil.mazesolver.services.impl

import com.marios.gavriil.mazesolver.Commons
import com.marios.gavriil.mazesolver.entities.Maze
import com.marios.gavriil.mazesolver.parsers.CsvParser
import com.marios.gavriil.mazesolver.services.RectangleService
import org.springframework.util.ResourceUtils
import spock.lang.Specification

class MazeServiceImplSpec extends Specification{

    private static final String CLASSPATH_FILES_MAZE_FOR_TEST_CSV = "classpath:files/mazeForTest.csv"

    MazeServiceImpl mazeService
    RectangleService rectangleService
    CsvParser csvParser
    Commons commons

    def setup() {
        csvParser = Mock(CsvParser)
        rectangleService = Mock(RectangleService)
        mazeService = new MazeServiceImpl(csvParser, rectangleService)
        commons = new Commons()
    }

    def "isValidLocation returns true"() {
        when:
        def isValidLocation = mazeService.isValidLocation(commons.buildMaze().getMaze(), 1, 0)

        then:
        noExceptionThrown()
        isValidLocation
    }

    def "isValidLocation returns false (row<0)"() {
        when:
        def isValidLocation = mazeService.isValidLocation(commons.buildMaze().getMaze(), -1, 1)

        then:
        noExceptionThrown()
        !isValidLocation
    }

    def "isValidLocation returns false (col<0)"() {
        when:
        def isValidLocation = mazeService.isValidLocation(commons.buildMaze().getMaze(), 1, -1)

        then:
        noExceptionThrown()
        !isValidLocation
    }

    def "isValidLocation returns false (maze is smaller than the cell we try to access)"() {
        given:
        Maze maze = Maze.builder().maze(new String[1][1]).build()
        when:
        def isValidLocation = mazeService.isValidLocation(maze.getMaze(), 1, 1)

        then:
        noExceptionThrown()
        !isValidLocation
    }

    def "create maze successfully"() {
        given:
        def path = ResourceUtils.getFile(CLASSPATH_FILES_MAZE_FOR_TEST_CSV).getPath()
        1 * csvParser.parse(path) >> commons.createExpectedArray()

        when:
        Maze maze = mazeService.createMaze(path)

        then:
        noExceptionThrown()
        maze == commons.buildMaze()
    }

    def "create maze throws exception on CsvParser"() {
        given:
        def path = ResourceUtils.getFile(CLASSPATH_FILES_MAZE_FOR_TEST_CSV).getPath()
        1 * csvParser.parse(path) >> {throw  new FileNotFoundException()}

        when:
        mazeService.createMaze(path)

        then:
        thrown(FileNotFoundException)
    }

    def "print path successfully"() {
        when:
        mazeService.printPath(commons.buildMaze(), commons.buildMazeSolution())

        then:
        noExceptionThrown()
    }
}
