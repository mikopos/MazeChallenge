package com.marios.gavriil.mazesolver.services.impl;

import com.marios.gavriil.mazesolver.entities.Maze;
import com.marios.gavriil.mazesolver.entities.Rectangle;
import com.marios.gavriil.mazesolver.parsers.CsvParser;
import com.marios.gavriil.mazesolver.services.MazeService;
import com.marios.gavriil.mazesolver.services.RectangleService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Service that contains all the logic
 * around the creation of the maze
 */

@Service
@RequiredArgsConstructor
public class MazeServiceImpl implements MazeService {

    private static final String FREE_RECTANGLE = "0";
    private static final String WALL = "1";
    private static final String START = "2";
    private static final String EXIT = "3";
    private static final String SOLUTION_PATH = "4";
    private static final char SPACE_CHAR = ' ';
    private static final char HASHTAG_CHAR = '#';
    private static final char START_CHAR = 'S';
    private static final char EXIT_CHAR = 'E';
    private static final char SOLUTION_PATH_CHAR = '.';

    private final CsvParser csvParser;
    private final RectangleService rectangleService;

    Logger logger = LoggerFactory.getLogger(MazeServiceImpl.class);

    /**
     * Method that gets the maze array and retrieves its height
     *
     * @param maze  maze array
     * @return      the height of the array
     */
    @Override
    public int getHeight(String[][] maze) {
        return maze.length;
    }

    /**
     * Method that gets the maze array and retrieves its width
     *
     * @param maze  maze array
     * @return      the width of the array
     */
    @Override
    public int getWidth(String[][] maze) {
        return maze[0].length;
    }

    /**
     * Method that checks if the current rectangle is a
     * valid location or not and returns true or false
     * accordingly
     *
     * @param maze  the maze array on which the calculation will take place
     * @param row   row of the rectangle
     * @param col   column of the rectangle
     * @return      true/false if the rectangle is valid for movement or not
     */
    @Override
    public boolean isValidLocation(String[][] maze, int row, int col) {
        return row >= 0 && row < getHeight(maze) && col >= 0 && col < getWidth(maze);
    }

    /**
     * Method that gets a path and creates the maze
     *
     * @param path  path to the csv file that contains the maze blueprint
     * @return      a maze {@link Maze}
     */
    @Override
    public Maze createMaze(String path) {
        String[][] mazeArray = csvParser.parse(path);
        boolean[][] visitedRectangles = new boolean[mazeArray.length][mazeArray[0].length];
        Rectangle start = null;
        Rectangle end = null;

        for (int i=0 ; i< mazeArray.length ; i++) {
            for (int j=0 ; j< mazeArray.length ; j++){
                if(mazeArray[i][j].equals(START)){
                    start = Rectangle.builder()
                            .x(i)
                            .y(j)
                            .build();
                }
                if(mazeArray[i][j].equals(EXIT)){
                    end = Rectangle.builder()
                            .x(i)
                            .y(j)
                            .build();
                }
            }
        }
        return Maze.builder()
                .maze(mazeArray)
                .start(start)
                .end(end)
                .visitedRectangles(visitedRectangles)
                .path(Collections.emptyList())
                .build();
    }

    /**
     * Method that prints the solution path
     * of the maze and visualizes it
     *
     * @param maze  maze {@link Maze} in which solution will be printed
     * @param path  list that contains the solution path
     */
    @Override
    public void printPath(Maze maze, List<Rectangle> path) {
        String[][] tempMaze = Arrays.stream(maze.getMaze())
                .map(String[]::clone)
                .toArray(String[][]::new);
        for (Rectangle coordinate : path) {
            if (rectangleService.isStart(maze.getStart(), coordinate.getX(), coordinate.getY()) ||
                    rectangleService.isExit(maze.getEnd(), coordinate.getX(), coordinate.getY())) {
                continue;
            }
            tempMaze[coordinate.getX()][coordinate.getY()] = SOLUTION_PATH;
        }
        logger.info(toString(tempMaze));
        logger.info(toString(maze, tempMaze));
    }

    /**
     * Method that visualizes the maze and its solution path
     *
     * @param maze          the maze that will be visualized
     * @param mazeArray     the maze array, that contains all
     *                      the information about the maze
     *                      and its solution path
     * @return              string tha visualizes with special chars
     *                      the maze and it solution path
     */
    public String toString(Maze maze, String[][] mazeArray) {
        StringBuilder result = new StringBuilder(getWidth(maze.getMaze()) * (getHeight(maze.getMaze()) + 1));
        result.append('\n');
        for (int row = 0; row < getHeight(maze.getMaze()); row++) {
            for (int col = 0; col < getWidth(maze.getMaze()); col++) {
                switch (mazeArray[row][col]) {
                    case FREE_RECTANGLE:
                        result.append(SPACE_CHAR);
                        break;
                    case WALL:
                        result.append(HASHTAG_CHAR);
                        break;
                    case START:
                        result.append(START_CHAR);
                        break;
                    case EXIT:
                        result.append(EXIT_CHAR);
                        break;
                    default:
                        result.append(SOLUTION_PATH_CHAR);
                        break;
                }
            }
            result.append('\n');
        }
        return result.toString();
    }

    /**
     * Method that returns a string with the coordinates
     * of each rectangle that is being part of the
     * solution path of the maze
     *
     * @param mazeArray         the maze array, that contains all
     *                          the information about the maze
     *                          and its solution path
     * @return                  a string with all the coordinates
     *                          of the rectangles of the solution path
     */
    public String toString(String[][] mazeArray) {
        StringBuilder result = new StringBuilder(mazeArray[0].length *  mazeArray.length + 1);
        result.append("(");
        for (int row = 0; row < mazeArray.length; row++) {
            for (int col = 0; col < mazeArray[0].length; col++) {
                switch (mazeArray[row][col]) {
                    case START:
                        result.append("(").append(row).append(":").append(col).append("(S))");
                        break;
                    case EXIT:
                        result.append("(").append(row).append(":").append(col).append("(G))");
                        break;
                    case SOLUTION_PATH:
                        result.append("(").append(row).append(":").append(col).append(")");
                        break;
                }
            }
        }
        result.append(")");
        return result.toString();
    }
}
