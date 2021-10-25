package com.marios.gavriil.mazesolver.services.impl;

import com.marios.gavriil.mazesolver.entities.Rectangle;
import com.marios.gavriil.mazesolver.services.RectangleService;
import org.springframework.stereotype.Service;

/**
 * Service that implements the functionality
 * needed for every Rectangle
 */

@Service
public class RectangleServiceImpl implements RectangleService {

    public static final String WALL = "1";

    /**
     * Method that identifies if the current
     * rectangle is the exit or not
     *
     * @param exit  exit rectangle
     * @param x     current rectangle's x coordinate
     * @param y     current rectangle's y coordinate
     * @return      true if the rectangle is the exit
     *              rectangle of false if it's not
     */
    @Override
    public boolean isExit(Rectangle exit, int x, int y) {
        return x == exit.getX() && y == exit.getY();
    }

    /**
     * Method that identifies if the current
     * rectangle is the start or not
     *
     * @param start     start rectangle
     * @param x         current rectangle's x coordinate
     * @param y         current rectangle's y coordinate
     * @return          true if the rectangle is the start
     *                  rectangle of false if it's not
     */
    @Override
    public boolean isStart(Rectangle start, int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    /**
     * Method that identifies if a rectangle
     * has been already explored or not
     *
     * @param visited   array that hold the information of
     *                  whether a rectangle is visited or not
     * @param row       current rectangle's row
     * @param col       current rectangle's column
     * @return          true if it has already been visited
     *                  or false if it hasn't
     */
    @Override
    public boolean isExplored(boolean[][] visited, int row, int col) {
        return visited[row][col];
    }

    /**
     * Method that identifies if a rectangle is a wall or not
     *
     * @param maze  maze array on which the calculation will take place
     * @param row   current rectangle's row
     * @param col   current rectangle's column
     * @return      true if the rectangle is wall of false if it is not
     */
    @Override
    public boolean isWall(String[][] maze, int row, int col) {
        return maze[row][col].equals(WALL);
    }

    /**
     * Method that sets if a rectangle has been visited or not
     *
     * @param visited   array that hold is being populated of
     *                  the appropriate value when a rectangle
     *                  is being visited
     * @param row       current rectangle's row
     * @param col       current rectangle's column
     * @param value     true/false if the rectangle is being visited or not
     */
    @Override
    public void setVisited(boolean[][] visited, int row, int col, boolean value) {
        visited[row][col] = value;
    }
}
