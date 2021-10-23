package com.marios.gavriil.mazesolver.services;

import com.marios.gavriil.mazesolver.entities.Rectangle;

public interface RectangleService {

    boolean isExit(Rectangle exit, int x, int y);

    boolean isStart(Rectangle start, int x, int y);

    boolean isExplored(boolean[][] visited, int row, int col);

    boolean isWall(String[][] maze, int row, int col);

    void setVisited(boolean[][] visited, int row, int col, boolean value);
}
