package com.marios.gavriil.mazesolver.services.impl;

import com.marios.gavriil.mazesolver.entities.Maze;
import com.marios.gavriil.mazesolver.entities.Rectangle;
import com.marios.gavriil.mazesolver.services.DfsService;
import com.marios.gavriil.mazesolver.services.MazeService;
import com.marios.gavriil.mazesolver.services.RectangleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service that contains the
 * DFS logic for solving the maze
 */

@Service
@RequiredArgsConstructor
public class DfsServiceImpl implements DfsService {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private final MazeService mazeService;
    private final RectangleService rectangleService;

    /**
     * Method that solves the maze and returns a list with
     * the path or an empty list in case there is no solution
     *
     * @param maze      the maze that is going to be solved
     * @return          the list with the rectangles of the solution path or
     *                  an empty list in case there is no solution
     */
    @Override
    public List<Rectangle> solve(Maze maze) {
        List<Rectangle> path = new ArrayList<>();
        if (explore(maze, maze.getStart().getX(),
                maze.getStart().getY(),
                path)) {
            return path;
        }
        return Collections.emptyList();
    }

    /**
     * Recursive method that explores the maze's rectangles to identify if
     * a rectangle is valid to move, if it is a wall, if it has already
     * been explored of if it is an exit. In case the rectangle is part
     * of the solution path is it being added in the path list and after
     * all the rectangles have been checked it returns true or false if
     * a path exists or not accordingly.
     *
     * @param maze  the maze in which the solution we are searching for
     * @param row   the row of the starting point
     * @param col   the column of the starting point
     * @param path  the list in which the path will be added
     * @return      true if a path exists or false if a path does not exist
     */
    private boolean explore(Maze maze, int row, int col, List<Rectangle> path) {
        if (!mazeService.isValidLocation(maze.getMaze(), row, col) ||
                rectangleService.isWall(maze.getMaze(), row, col) ||
                rectangleService.isExplored(maze.getVisitedRectangles(), row, col)) {
            return false;
        }

        path.add(new Rectangle(row, col));
        rectangleService.setVisited(maze.getVisitedRectangles(), row, col, true);

        if (rectangleService.isExit(maze.getEnd(), row, col)) {
            return true;
        }

        for (int[] direction : DIRECTIONS) {
            Rectangle rectangle = getNextRectangle(row, col, direction[0], direction[1]);
            if (explore(maze, rectangle.getX(), rectangle.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    /**
     * Method that retrieves a neighbour rectangle from the
     * current one according to the direction provided from
     * the directions array
     *
     * @param row   rectangle's current row
     * @param col   rectangle's current column
     * @param i     x cordinate from directions array
     * @param j     y coordinate from directions array
     * @return      the new Rectangle {@link Rectangle}
     */
    private Rectangle getNextRectangle(int row, int col, int i, int j) {
        return new Rectangle(row + i, col + j);
    }
}
