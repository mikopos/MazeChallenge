package com.marios.gavriil.mazesolver.services.impl

import com.marios.gavriil.mazesolver.Commons
import spock.lang.Specification

class RectangleServiceImplSpec extends Specification{

    RectangleServiceImpl rectangleService
    Commons commons

    def setup() {
        rectangleService = new RectangleServiceImpl()
        commons = new Commons()
    }

    def "isExit returns true"() {
        when:
        def isExit = rectangleService.isExit(commons.buildMaze().getEnd(), 1, 2)

        then:
        isExit
    }

    def "isExit returns false"() {
        when:
        def isExit = rectangleService.isExit(commons.buildMaze().getEnd(), 1, 1)

        then:
        !isExit
    }

    def "isStart returns true"() {
        when:
        def isStart = rectangleService.isStart(commons.buildMaze().getStart(), 1, 0)

        then:
        isStart
    }

    def "isStart returns false"() {
        when:
        def isStart = rectangleService.isStart(commons.buildMaze().getStart(), 1, 1)

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
        def isWall = rectangleService.isWall(commons.buildMaze().getMaze(), 0, 0)

        then:
        isWall
    }

    def "isWall returns false"() {
        when:
        def isWall = rectangleService.isWall(commons.buildMaze().getMaze(), 1, 1)

        then:
        !isWall
    }
}
