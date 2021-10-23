package com.marios.gavriil.mazesolver.services.impl;

import com.marios.gavriil.mazesolver.entities.Rectangle;
import com.marios.gavriil.mazesolver.services.RectangleService;
import org.springframework.stereotype.Service;

@Service
public class RectangleServiceImpl implements RectangleService {

    @Override
    public boolean isExit(Rectangle exit, int x, int y) {
        return x == exit.getX() && y == exit.getY();
    }

    @Override
    public boolean isStart(Rectangle start, int x, int y) {
        return x == start.getX() && y == start.getY();
    }

    @Override
    public boolean isExplored(boolean[][] visited, int row, int col) {
        return visited[row][col];
    }

    @Override
    public boolean isWall(String[][] maze, int row, int col) {
        return maze[row][col].equals("1");
    }

    @Override
    public void setVisited(boolean[][] visited, int row, int col, boolean value) {
        visited[row][col] = value;
    }
}
