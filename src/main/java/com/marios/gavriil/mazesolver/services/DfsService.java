package com.marios.gavriil.mazesolver.services;

import com.marios.gavriil.mazesolver.entities.Maze;
import com.marios.gavriil.mazesolver.entities.Rectangle;

import java.util.List;

public interface DfsService {

    List<Rectangle> solve(Maze maze);
}
