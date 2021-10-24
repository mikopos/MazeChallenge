package com.marios.gavriil.mazesolver.services.impl

import com.marios.gavriil.mazesolver.entities.Maze
import com.marios.gavriil.mazesolver.entities.Rectangle
import com.marios.gavriil.mazesolver.parsers.CsvParser
import com.marios.gavriil.mazesolver.services.DfsService
import com.marios.gavriil.mazesolver.services.MazeService
import com.marios.gavriil.mazesolver.services.RectangleService
import spock.lang.Specification

class DfsServiceImplSpec extends Specification{

    DfsServiceImpl dfsServiceImpl
    MazeService mazeService
    RectangleService rectangleService
    CsvParser csvParser

    def setup() {
        csvParser= new CsvParser()
        rectangleService = new RectangleServiceImpl()
        mazeService = new MazeServiceImpl(csvParser, rectangleService)
        dfsServiceImpl = new DfsServiceImpl(mazeService, rectangleService)
    }

    def "solve happy path"() {

        when:
        def list = dfsServiceImpl.solve(buildMaze())

        then:
        noExceptionThrown()
        list == buildMazeSolution()
    }

    def "solve with explore to return false"() {
        given:
        Maze maze = Maze.builder()
                .start(new Rectangle(1,0))
                .end(new Rectangle(1,3))
                .maze(createExpectedArray())
                .visitedRectangles(new boolean[3][3])
                .path(new ArrayList<String[][]>())
                .build()

        when:
        def list = dfsServiceImpl.solve(maze)

        then:
        noExceptionThrown()
        list == Collections.emptyList()
    }

    def "explore happy path"() {
        when:
        boolean isExplored = dfsServiceImpl.explore(buildMaze(),1,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        isExplored
    }

    def "explore with isValid to return false"() {
        when:
        boolean isExplored = dfsServiceImpl.explore(buildMaze(),4,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        !isExplored
    }

    def "explore with isWall to return true "() {
        when:
        boolean isExplored = dfsServiceImpl.explore(buildMaze(),0,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        !isExplored
    }

    def "explore with isExit to return true"() {
        when:
        boolean isExplored = dfsServiceImpl.explore(buildMaze(),1,2, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        isExplored
    }

    def "getNextCoordinate happy path"() {
        given:
        Rectangle expectedRectangle = new Rectangle(2,2)

        when:
        Rectangle rectangle = dfsServiceImpl.getNextCoordinate(1,1,1,1)

        then:
        noExceptionThrown()
        rectangle == expectedRectangle
    }

    private static Maze buildMaze() {
        return Maze.builder()
                .start(new Rectangle(1,0))
                .end(new Rectangle(1,2))
                .maze(createExpectedArray())
                .visitedRectangles(new boolean[3][3])
                .path(new ArrayList<String[][]>())
                .build()
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

    private static List<Rectangle> buildMazeSolution() {
        List<Rectangle> rectangleList = new ArrayList<>()
        rectangleList.add(new Rectangle(1,0))
        rectangleList.add(new Rectangle(1,1))
        rectangleList.add(new Rectangle(1,2))

        return rectangleList
    }
}
