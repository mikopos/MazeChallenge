package com.marios.gavriil.mazesolver;

import com.marios.gavriil.mazesolver.entities.Maze
import com.marios.gavriil.mazesolver.entities.Rectangle


 class Commons {

      static Maze buildMaze() {
        return Maze.builder()
                .start(new Rectangle(1,0))
                .end(new Rectangle(1,2))
                .maze(createExpectedArray())
                .visitedRectangles(new boolean[3][3])
                .path(new ArrayList<String[][]>())
                .build()
    }

     static String[][] createExpectedArray() {
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

     static List<Rectangle> buildMazeSolution() {
        List<Rectangle> rectangleList = new ArrayList<>()
        rectangleList.add(new Rectangle(1,0))
        rectangleList.add(new Rectangle(1,1))
        rectangleList.add(new Rectangle(1,2))

        return rectangleList
    }
}
