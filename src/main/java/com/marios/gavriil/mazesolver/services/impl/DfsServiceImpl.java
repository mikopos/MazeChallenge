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

@Service
@RequiredArgsConstructor
public class DfsServiceImpl implements DfsService {

    private static final int[][] DIRECTIONS = { { 0, 1 }, { 1, 0 }, { 0, -1 }, { -1, 0 } };

    private final MazeService mazeService;
    private final RectangleService rectangleService;

    @Override
    public List<Rectangle> solve(Maze maze) {
        List<Rectangle> path = new ArrayList<>();
        if (explore(maze, maze.getStart()
                        .getX(),
                maze.getStart()
                        .getY(),
                path)) {
            return path;
        }
        return Collections.emptyList();
    }

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
            Rectangle rectangle = getNextCoordinate(row, col, direction[0], direction[1]);
            if (explore(maze, rectangle.getX(), rectangle.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
    }

    private Rectangle getNextCoordinate(int row, int col, int i, int j) {
        return new Rectangle(row + i, col + j);
    }
}
