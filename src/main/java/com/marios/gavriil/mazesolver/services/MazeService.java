package com.marios.gavriil.mazesolver.services;

import com.marios.gavriil.mazesolver.entities.Maze;
import com.marios.gavriil.mazesolver.entities.Rectangle;

import java.util.List;

public interface MazeService {

    int getHeight(String[][] maze);

    int getWidth(String[][] maze);

    boolean isValidLocation(String[][] maze, int row, int col);

    Maze createMaze(String path);

    void printPath(Maze maze, List<Rectangle> path);
}
