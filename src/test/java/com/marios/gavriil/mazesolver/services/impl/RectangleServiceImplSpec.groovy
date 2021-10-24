package com.marios.gavriil.mazesolver.services.impl

import com.marios.gavriil.mazesolver.entities.Maze
import com.marios.gavriil.mazesolver.entities.Rectangle
import spock.lang.Specification

class RectangleServiceImplSpec extends Specification{

    RectangleServiceImpl rectangleService

    def setup() {
        rectangleService = new RectangleServiceImpl()
    }

    def "isExit returns true"() {
        when:
        def isExit = rectangleService.isExit(buildMaze().getEnd(), 1, 2)

        then:
        isExit
    }

    def "isExit returns false"() {
        when:
        def isExit = rectangleService.isExit(buildMaze().getEnd(), 1, 1)

        then:
        !isExit
    }

    def "isStart returns true"() {
        when:
        def isStart = rectangleService.isStart(buildMaze().getStart(), 1, 0)

        then:
        isStart
    }

    def "isStart returns false"() {
        when:
        def isStart = rectangleService.isStart(buildMaze().getStart(), 1, 1)

        then:
        !isStart
    }

    def "isExplored returns true"() {
        given:
        boolean [][] visited = new boolean[3][3]
        visited[1][0] = true
        when:
        def isExplored = rectangleService.isExplored(visited, 1, 0)

        then:
        isExplored
    }

    def "isExplored returns false"() {
        given:
        boolean [][] visited = new boolean[3][3]
        visited[1][0] = false
        when:
        def isExplored = rectangleService.isExplored(visited, 1, 0)

        then:
        !isExplored
    }

    def "isWall returns true"() {
        when:
        def isWall = rectangleService.isWall(buildMaze().getMaze(), 0, 0)

        then:
        isWall
    }

    def "isWall returns false"() {
        when:
        def isWall = rectangleService.isWall(buildMaze().getMaze(), 1, 1)

        then:
        !isWall
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
}
