package com.marios.gavriil.mazesolver.entities;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Entity that represents the Maze
 */

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Maze {

    /**
     * The actual maze
     */
    private String[][] maze;
    /**
     * Array that holds if a rectangle hac been visited or not
     */
    private boolean[][] visitedRectangles;
    /**
     * Starting point
     */
    private Rectangle start;
    /**
     * Exit point
     */
    private Rectangle end;
    /**
     * List with a 2D array that hold the rectangles that consisting the solution path
     */
    private List<String[][]> path;
}
