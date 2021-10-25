package com.marios.gavriil.mazesolver.entities;

import lombok.*;

/**
 * Entity that represents each rectangle of the maze
 */

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@EqualsAndHashCode
public class Rectangle {

    /**
     * Coordinate x of the rectangle
     */
    private final int x;

    /**
     * Coordinate y of the rectangle
     */
    private final int y;

}
