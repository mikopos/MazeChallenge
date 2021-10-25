package com.marios.gavriil.mazesolver.services.impl

import com.marios.gavriil.mazesolver.Commons
import com.marios.gavriil.mazesolver.entities.Maze
import com.marios.gavriil.mazesolver.entities.Rectangle
import com.marios.gavriil.mazesolver.parsers.CsvParser
import com.marios.gavriil.mazesolver.services.MazeService
import com.marios.gavriil.mazesolver.services.RectangleService
import spock.lang.Specification

class DfsServiceImplSpec extends Specification{

    DfsServiceImpl dfsServiceImpl
    MazeService mazeService
    RectangleService rectangleService
    CsvParser csvParser
    Commons commons

    def setup() {
        csvParser= new CsvParser()
        rectangleService = new RectangleServiceImpl()
        mazeService = new MazeServiceImpl(csvParser, rectangleService)
        dfsServiceImpl = new DfsServiceImpl(mazeService, rectangleService)
        commons = new Commons()
    }

    def "solve happy path"() {

        when:
        def list = dfsServiceImpl.solve(commons.buildMaze())

        then:
        noExceptionThrown()
        list == commons.buildMazeSolution()
    }

    def "solve with explore to return false"() {
        given:
        Maze maze = Maze.builder()
                .start(new Rectangle(1,0))
                .end(new Rectangle(1,3))
                .maze(commons.createExpectedArray())
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
        boolean isExplored = dfsServiceImpl.explore(commons.buildMaze(),1,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        isExplored
    }

    def "explore with isValid to return false"() {
        when:
        boolean isExplored = dfsServiceImpl.explore(commons.buildMaze(),4,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        !isExplored
    }

    def "explore with isWall to return true "() {
        when:
        boolean isExplored = dfsServiceImpl.explore(commons.buildMaze(),0,0, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        !isExplored
    }

    def "explore with isExit to return true"() {
        when:
        boolean isExplored = dfsServiceImpl.explore(commons.buildMaze(),1,2, new ArrayList<Rectangle>())
        then:
        noExceptionThrown()
        isExplored
    }

    def "getNextRectangle happy path"() {
        given:
        Rectangle expectedRectangle = new Rectangle(2,2)

        when:
        Rectangle rectangle = dfsServiceImpl.getNextRectangle(1,1,1,1)

        then:
        noExceptionThrown()
        rectangle == expectedRectangle
    }
}
