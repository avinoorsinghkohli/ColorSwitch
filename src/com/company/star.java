package com.company;

import javafx.geometry.Bounds;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.*;

import java.io.Serializable;

public class star implements Serializable
{
    int x_centre;
    int y_centre;
    transient ImageView star12;
    star(){}
    ImageView add_star(int x,int y)
    {
        x_centre=x;
        y_centre=y;
        star12 = new ImageView(new Image("star.jpg"));
        star12.setFitHeight(40);
        star12.setPreserveRatio(true);
        star12.setX(x);
        star12.setY(y);
        return star12;
    }
    public static boolean isCollide(Arc x, ImageView y)
    {
        Bounds RectA = x.localToScene(x.getBoundsInLocal());
        Bounds RectB = y.localToScene(y.getBoundsInLocal());
        return RectB.intersects(RectA);
    }
}
