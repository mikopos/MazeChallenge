package com.marios.gavriil.mazesolver;

import com.marios.gavriil.mazesolver.entities.Maze;
import com.marios.gavriil.mazesolver.entities.Rectangle;
import com.marios.gavriil.mazesolver.services.DfsService;
import com.marios.gavriil.mazesolver.services.MazeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;

import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class MazeSolverApplication implements ApplicationRunner {

    private static final String CLASSPATH_FILES_MAZE_START_AT_EDGE_CSV = "classpath:files/mazeStartAtEdge.csv";

    private final MazeService mazeService;
    private final DfsService dfsService;

    public static void main(String[] args) {
        SpringApplication.run(MazeSolverApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Maze maze = mazeService.createMaze(ResourceUtils.getFile(CLASSPATH_FILES_MAZE_START_AT_EDGE_CSV).getPath());
        List<Rectangle> rectangleList = dfsService.solve(maze);
        mazeService.printPath(maze, rectangleList);
    }
}
