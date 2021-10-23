package com.marios.gavriil.mazesolver.entities;

import lombok.*;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
public class Rectangle {

    private final int x;
    private final int y;
    private Rectangle parent;

}