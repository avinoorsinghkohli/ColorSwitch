package com.company;

import javafx.geometry.Bounds;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Shape;

import java.io.Serializable;
import java.util.Random;

public class color_changer implements Serializable
{

    transient Group root;
    int x_centre;
    int y_centre;
    color_changer(){}
    void spawn(int x,int y)
    {
        x_centre=x;
        y_centre=y;
        Arc arc = new Arc(x, y, 15, 15, 0, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(50, 219, 240));

        Arc arc2 = new Arc(x, y, 15, 15, 90, 90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.rgb(144, 13, 255));

        Arc arc3 = new Arc(x, y, 15, 15, 180, 90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.rgb(255, 1, 129));

        Arc arc4 = new Arc(x, y, 15, 15, 270, 90);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.rgb(250, 225, 0));

        root= new Group(arc, arc2, arc3, arc4);
    }
    public static void assign_color(Shape a)

    {
        Random random=new Random();
        Paint tempo=a.getFill();
        int temp= random.nextInt(4)+1;
        if(temp==1)
        {
            a.setFill(Color.rgb(50, 219, 240));
        }
        if(temp==2)
        {
            a.setFill(Color.rgb(144, 13, 255));
        }
        if(temp==3)
        {
            a.setFill(Color.rgb(255, 1, 129));
        }
        if(temp==4)
        {
            a.setFill(Color.rgb(250, 225, 0));
        }
        while(a.getFill()==tempo)
        {
            temp= random.nextInt(4)+1;
            if(temp==1)
            {
                a.setFill(Color.rgb(50, 219, 240));
            }
            if(temp==2)
            {
                a.setFill(Color.rgb(144, 13, 255));
            }
            if(temp==3)
            {
                a.setFill(Color.rgb(255, 1, 129));
            }
            if(temp==4)
            {
                a.setFill(Color.rgb(250, 225, 0));
            }
        }
    }

    public static boolean isCollide(Arc x, Group y)
    {
        Bounds RectA = x.localToScene(x.getBoundsInLocal());
        Bounds RectB = y.localToScene(y.getBoundsInLocal());
        return RectB.intersects(RectA);
    }
}
