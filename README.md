# Maze Solver

Maze solver is a very common project that is based on [Graph Theory](https://en.wikipedia.org/wiki/Graph_theory).
In this implementation, it takes a .csv file as argument in order to create the graph and then it uses a 
[DFS(Depth-First search) algorithm](https://en.wikipedia.org/wiki/Depth-first_search) in order to find its solution.

##Technical Design
The imported .csv file contains values of 
* 0 for free space
* 1 for walls
* 2 for entry point
* 3 for exit point

This csv file if being fetched using its path from a csv parser, which returns a 2D array that represents the maze.

About the DFS algorithm even though it is not the most optimized like an A* or Dijkstra, it is a very good one for 
demonstration purposes. Diving deeper in the algorithm we figure out that it works like this :

Imagine the maze as a graph and every maze's rectangle as a graph node. DFS starts from starting node and continues visiting the next one, and simultaneously it
adds the node to a stack in order to remember the visited nodes. This process continues recursively until it reaches a dead end,
or it finds the exit node. In case it reaches a dead end, it retrieves the previous node from the stack and moves to it. It is happening also recursively 
until the DFS finds the solution path or the stack gets empty.

## Getting Started

These instructions will help you to build and run the project.

###Prerequisites
* JDK 11
* Maven

###Build project

* Navigate inside project folder
* Run command ```mvn clean install```

###Run project
* Navigate inside project folder
* Run command ```mvn spring-boot:run```

##Built With

* [JDK 11](https://www.oracle.com/java/technologies/javase/11all-relnotes.html)
* [SpringBoot](https://spring.io/projects/spring-boot)
* [Spock](https://spockframework.org/)
* [Maven](https://maven.apache.org/)

## Versioning
The current version is v1.0.0 .

We use [SemVer](http://semver.org/) for versioning.

## Acknowledgments

* [Baeldung](https://www.baeldung.com/java-solve-maze)
* [PurpleBooth](https://gist.github.com/PurpleBooth)