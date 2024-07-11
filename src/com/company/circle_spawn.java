package com.company;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

public class circle_spawn {
    Group root;
    circle_spawn(int x_centre,int y_centre,int r,int r2,Paint a,Paint b,Paint c,Paint d,boolean anti)
    {
        root = new Group();
        spawn(x_centre,y_centre,r,r2,a,b,c,d,anti);
    }
    circle_spawn(int x_centre,int y_centre,int r,int r2)
    {
        root = new Group();
        spawn(x_centre,y_centre,r,r2,Color.rgb(255, 1, 129),Color.rgb(50, 219, 240),Color.rgb(250, 225, 0),Color.rgb(144, 13, 255),false);
    }
    void spawn(int x_centre, int y_centre, int r, int r2,Paint a,Paint b,Paint c,Paint d,boolean anti)
    {
        Arc arc = new Arc(x_centre, y_centre, r, r, 0, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 1, 129));
        Arc arc11 = new Arc(x_centre, y_centre, r2, r2, 0, 90);
        arc11.setType(ArcType.ROUND);
        arc11.setFill(Color.rgb(255, 1, 129));


        Arc arc2 = new Arc(x_centre, y_centre, r, r, 90, 90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.rgb(50, 219, 240));
        Arc arc21 = new Arc(x_centre, y_centre, r2, r2, 90, 90);
        arc21.setType(ArcType.ROUND);
        arc21.setFill(Color.rgb(50, 219, 240));

        Arc arc3 = new Arc(x_centre, y_centre, r, r, 180, 90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.rgb(144, 13, 255));
        Arc arc31 = new Arc(x_centre, y_centre, r2, r2, 180, 90);
        arc31.setType(ArcType.ROUND);
        arc31.setFill(Color.rgb(144, 13, 255));

        Arc arc4 = new Arc(x_centre, y_centre, r, r, 270, 90);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.rgb(250, 225, 0));
        Arc arc41 = new Arc(x_centre, y_centre, r2, r2, 270, 90);
        arc41.setType(ArcType.ROUND);
        arc41.setFill(Color.rgb(250, 225, 0));

        Shape shape1 = Shape.subtract(arc, arc11);
        shape1.setFill(a);
        Shape shape2 = Shape.subtract(arc2, arc21);
        shape2.setFill(b);
        Shape shape3 = Shape.subtract(arc3, arc31);
        shape3.setFill(c);
        Shape shape4 = Shape.subtract(arc4, arc41);
        shape4.setFill(d);
        root = new Group(shape1, shape2, shape3, shape4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000 + Game.difficult * 1000));
        rotateTransition.setNode(root);
        if(!anti)
            rotateTransition.setByAngle(360);
        else
            rotateTransition.setByAngle(-360);
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }
}