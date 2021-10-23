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
import java.util.List;

@Service
@RequiredArgsConstructor
public class MazeServiceImpl implements MazeService {

    Logger logger = LoggerFactory.getLogger(MazeServiceImpl.class);

    private final CsvParser csvParser;
    private final RectangleService rectangleService;

    @Override
    public int getHeight(String[][] maze) {
        return maze.length;
    }

    @Override
    public int getWidth(String[][] maze) {
        return maze[0].length;
    }

    @Override
    public boolean isValidLocation(String[][] maze, int row, int col) {
        return row >= 0 && row < getHeight(maze) && col >= 0 && col < getWidth(maze);
    }

    @Override
    public Maze createMaze(String path) {
        String[][] mazeArray = csvParser.parse(path);
        boolean[][] visitedRectangles = new boolean[mazeArray.length][mazeArray[0].length];
        Rectangle start = null;
        Rectangle end = null;

        for (int i=0 ; i< mazeArray.length ; i++) {
            for (int j=0 ; j< mazeArray.length ; j++){
                if(mazeArray[i][j].equals("2")){
                    start = Rectangle.builder()
                            .x(i)
                            .y(j)
                            .build();
                }
                if(mazeArray[i][j].equals("3")){
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
                .build();
    }

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
            tempMaze[coordinate.getX()][coordinate.getY()] = "4";
        }
        logger.info(toString(maze, tempMaze));
    }

    public String toString(Maze maze, String[][] mazeArray) {
        StringBuilder result = new StringBuilder(getWidth(maze.getMaze()) * (getHeight(maze.getMaze()) + 1));
        for (int row = 0; row < getHeight(maze.getMaze()); row++) {
            for (int col = 0; col < getWidth(maze.getMaze()); col++) {
                if (mazeArray[row][col].equals("0")) {
                    result.append(' ');
                } else if (mazeArray[row][col].equals("1")) {
                    result.append('#');
                } else if (mazeArray[row][col].equals("2")) {
                    result.append('S');
                } else if (mazeArray[row][col].equals("3")) {
                    result.append('E');
                } else {
                    result.append('.');
                }
            }
            result.append('\n');
        }
        return result.toString();
    }
}
