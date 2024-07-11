package com.company;

import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Shape;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

public class Ball
{
    Arc ball;

    Ball()
    {

    }
    void color_change()
    {

    }
    public Bounds localToScene()
    {
        return this.ball.localToScene(this.ball.getBoundsInLocal());
    }

}
