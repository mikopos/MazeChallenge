package com.marios.gavriil.mazesolver.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Maze {

    private String[][] maze;
    private boolean[][] visitedRectangles;
    private Rectangle start;
    private Rectangle end;
    private List<String[][]> path;
}
