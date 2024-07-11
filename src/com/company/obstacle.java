package com.company;

import javafx.animation.AnimationTimer;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.io.Serializable;

public abstract class obstacle implements Serializable
{
    private double centre;
    private int x1;
    abstract void spawn(int x,int y);
    abstract boolean collision(Shape a);
    int x_centre,y_centre;
    transient Group root;
    int safe_distance;
}
class circle extends obstacle
{
    int r=175;
    circle(int x_spawn,int y_spawn)
    {

        x_centre=x_spawn;
        y_centre=y_spawn;
        spawn(x_spawn,y_spawn);
    }
    @Override
    void spawn(int x_spawn,int y_spawn)
    {
        safe_distance=200;
        x_centre=x_spawn;
        y_centre=y_spawn;
        Arc arc = new Arc(x_centre, y_centre, r, r, 0, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 1, 129));
        Arc arc11 = new Arc(x_centre, y_centre, r-25, r-25, 0, 90);
        arc11.setType(ArcType.ROUND);
        arc11.setFill(Color.rgb(255, 1, 129));


        Arc arc2 = new Arc(x_centre, y_centre, r, r, 90, 90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.rgb(50, 219, 240));
        Arc arc21 = new Arc(x_centre, y_centre, r-25, r-25, 90, 90);
        arc21.setType(ArcType.ROUND);
        arc21.setFill(Color.rgb(50, 219, 240));

        Arc arc3 = new Arc(x_centre, y_centre, r, r, 180, 90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.rgb(144, 13, 255));
        Arc arc31 = new Arc(x_centre, y_centre, r-25, r-25, 180, 90);
        arc31.setType(ArcType.ROUND);
        arc31.setFill(Color.rgb(144, 13, 255));

        Arc arc4 = new Arc(x_centre, y_centre, r, r, 270, 90);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.rgb(250, 225, 0));
        Arc arc41 = new Arc(x_centre, y_centre, r-25, r-25, 270, 90);
        arc41.setType(ArcType.ROUND);
        arc41.setFill(Color.rgb(250, 225, 0));

        Shape shape1 = Shape.subtract(arc, arc11);
        shape1.setFill(Color.rgb(255, 1, 129));
        Shape shape2 = Shape.subtract(arc2, arc21);
        shape2.setFill(Color.rgb(50, 219, 240));
        Shape shape3 = Shape.subtract(arc3, arc31);
        shape3.setFill(Color.rgb(144, 13, 255));
        Shape shape4 = Shape.subtract(arc4, arc41);
        shape4.setFill(Color.rgb(250, 225, 0));
        root=new Group(shape1,shape2,shape3,shape4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000+Game.difficult*1000));
        rotateTransition.setNode(root);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }

    @Override
    boolean collision(Shape a)
    {
        for(int i=0;i<root.getChildren().size();i++)
        {
            Shape intersect = Shape.intersect((Shape) root.getChildren().get(i),a);
            if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) root.getChildren().get(i)).getFill().equals(a.getFill())))
            {
                    return true;
            }
        }
        return false;
    }


}
//class square extends obstacle implements display
//{
//    square()
//    {
//
//    }
//    @Override
//    public void display()
//    {
//
//    }
//
//    @Override
//    void spawn()
//    {
//
//    }
//
//    @Override
//    boolean collision(Shape a)
//    {
//        return false;
//    }
//
//
//}

class double_circle extends obstacle implements display
{
    int count=0;
    double_circle(int x,int y)
    {
        spawn(x,y);

    }
    @Override
    public void display()
    {

    }
    void spawns(int x1,int x2,Paint a,Paint b,Paint c,Paint d)
    {

        safe_distance=175;
        Arc arc = new Arc(x1, x2, 150, 150, 0, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 1, 129));
        Arc arc11 = new Arc(x1, x2, 125, 125, 0, 90);
        arc11.setType(ArcType.ROUND);
        arc11.setFill(Color.rgb(255, 1, 129));


        Arc arc2 = new Arc(x1, x2, 150, 150, 90, 90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.BLUE);
        Arc arc21 = new Arc(x1, x2, 125, 125, 90, 90);
        arc21.setType(ArcType.ROUND);
        arc21.setFill(Color.BLUE);

        Arc arc3 = new Arc(x1, x2, 150, 150, 180, 90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.RED);
        Arc arc31 = new Arc(x1, x2, 125, 125, 180, 90);
        arc31.setType(ArcType.ROUND);
        arc31.setFill(Color.RED);

        Arc arc4 = new Arc(x1, x2, 150, 150, 270, 90);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.rgb(250, 225, 0));
        Arc arc41 = new Arc(x1, x2, 125, 125, 270, 90);
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

        Group root2=new Group(shape1,shape2,shape3,shape4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000));
        rotateTransition.setNode(root2);
        rotateTransition.setByAngle(360);
        count++;
        if(count==2)
        {
            count=0;
            rotateTransition.setByAngle(-360);
        }
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        root.getChildren().add(root2);
    }

    @Override
    void spawn(int x,int y)
    {
        x_centre=x;
        y_centre=y;
        root=new Group();
        spawns(x-150,y,Color.rgb(144, 13, 255),Color.rgb(255, 1, 129),Color.rgb(50, 219, 240),Color.rgb(250, 225, 0));
        spawns(x+150,y,Color.rgb(255, 1, 129),Color.rgb(144, 13, 255),Color.rgb(250, 225, 0),Color.rgb(50, 219, 240));
    }

    @Override
    boolean collision(Shape a)
    {
        for(int i=0;i<root.getChildren().size();i++)
        {
            Group temp= (Group)root.getChildren().get(i);
            for(int j=0;j<temp.getChildren().size();j++)
            {
                Shape intersect = Shape.intersect((Shape) temp.getChildren().get(j),a);
                if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) temp.getChildren().get(j)).getFill().equals(a.getFill())))
                {
                    return true;
                }
            }

        }
        return false;
    }


}

class up_circle extends obstacle implements display
{
    int count=0;
    up_circle(int x,int y)
    {
        spawn(x,y);
    }
    @Override
    public void display()
    {

    }


    void spawns(int x1,int x2,Paint a,Paint b,Paint c,Paint d)
    {
        safe_distance=325;
        Arc arc = new Arc(x1, x2, 150, 150, 0, 90);
        arc.setType(ArcType.ROUND);
        arc.setFill(Color.rgb(255, 1, 129));
        Arc arc11 = new Arc(x1, x2, 125, 125, 0, 90);
        arc11.setType(ArcType.ROUND);
        arc11.setFill(Color.rgb(255, 1, 129));


        Arc arc2 = new Arc(x1, x2, 150, 150, 90, 90);
        arc2.setType(ArcType.ROUND);
        arc2.setFill(Color.BLUE);
        Arc arc21 = new Arc(x1, x2, 125, 125, 90, 90);
        arc21.setType(ArcType.ROUND);
        arc21.setFill(Color.BLUE);

        Arc arc3 = new Arc(x1, x2, 150, 150, 180, 90);
        arc3.setType(ArcType.ROUND);
        arc3.setFill(Color.RED);
        Arc arc31 = new Arc(x1, x2, 125, 125, 180, 90);
        arc31.setType(ArcType.ROUND);
        arc31.setFill(Color.RED);

        Arc arc4 = new Arc(x1, x2, 150, 150, 270, 90);
        arc4.setType(ArcType.ROUND);
        arc4.setFill(Color.YELLOW);
        Arc arc41 = new Arc(x1, x2, 125, 125, 270, 90);
        arc41.setType(ArcType.ROUND);
        arc41.setFill(Color.YELLOW);

        Shape shape1 = Shape.subtract(arc, arc11);
        shape1.setFill(a);
        Shape shape2 = Shape.subtract(arc2, arc21);
        shape2.setFill(b);
        Shape shape3 = Shape.subtract(arc3, arc31);
        shape3.setFill(c);
        Shape shape4 = Shape.subtract(arc4, arc41);
        shape4.setFill(d);

        Group root2=new Group(shape1,shape2,shape3,shape4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000));
        rotateTransition.setNode(root2);
        rotateTransition.setByAngle(360);
        count++;
        if(count==2)
        {
            count=0;
            rotateTransition.setByAngle(-360);
        }
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
        root.getChildren().add(root2);
    }

    @Override
    void spawn(int x,int y)
    {
        x_centre=x;
        y_centre=y;
        root=new Group();
        spawns(x,y-150,Color.rgb(144, 13, 255),Color.rgb(255, 1, 129),Color.rgb(50, 219, 240),Color.rgb(250, 225, 0));
        spawns(x,y+150,Color.rgb(250, 225, 0),Color.rgb(50, 219, 240),Color.rgb(255, 1, 129),Color.rgb(144, 13, 255));
    }

    @Override
    boolean collision(Shape a)
    {
        for(int i=0;i<root.getChildren().size();i++)
        {
            Group temp= (Group)root.getChildren().get(i);
            for(int j=0;j<temp.getChildren().size();j++)
            {
                Shape intersect = Shape.intersect((Shape) temp.getChildren().get(j),a);
                if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) temp.getChildren().get(j)).getFill().equals(a.getFill())))
                {
                    return true;
                }
            }

        }
        return false;
    }

}

class plus extends obstacle implements display
{
    plus(int x,int y)
    {
        spawn(x,y);
    }
    @Override
    public void display()
    {

    }
    @Override
    void spawn(int x,int y)
    {
        safe_distance=225;
        x_centre=x;
        y_centre=y;
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x_centre);
        rectangle.setY(y_centre);
        rectangle.setHeight(20);
        rectangle.setWidth(200);
        rectangle.setFill(Color.rgb(250, 225, 0));
        Rotate rotate = new Rotate();
        rotate.setAngle(90);
        rotate.setPivotX(x_centre);
        rotate.setPivotY(y_centre);
        rectangle.getTransforms().addAll(rotate);

        Rectangle rectangle2= new Rectangle();
        rectangle2.setX(x_centre+20);
        rectangle2.setY(y_centre-10);
        rectangle2.setHeight(20);
        rectangle2.setWidth(200);
        rectangle2.setFill(Color.rgb(255, 1, 129));
        Rotate rotate2 = new Rotate();
        rotate2.setAngle(180);
        rotate2.setPivotX(x_centre);
        rotate2.setPivotY(y_centre);
        rectangle2.getTransforms().addAll(rotate2);

        Rectangle rectangle3= new Rectangle();
        rectangle3.setX(x_centre);
        rectangle3.setY(y_centre-10);
        rectangle3.setHeight(20);
        rectangle3.setWidth(200);
        rectangle3.setFill(Color.rgb(144, 13, 255));
        Rotate rotate3= new Rotate();
        rotate3.setAngle(0);
        rotate3.setPivotX(x_centre);
        rotate3.setPivotY(y_centre);
        rectangle3.getTransforms().addAll(rotate3);

        Rectangle rectangle4= new Rectangle();
        rectangle4.setX(x_centre);
        rectangle4.setY(y_centre-20);
        rectangle4.setHeight(20);
        rectangle4.setWidth(200);
        rectangle4.setFill(Color.rgb(50, 219, 240));
        Rotate rotate4 = new Rotate();
        rotate4.setAngle(-90);
        rotate4.setPivotX(x_centre);
        rotate4.setPivotY(y_centre);
        rectangle4.getTransforms().addAll(rotate4);

        root=new Group(rectangle,rectangle2,rectangle3,rectangle4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000));
        rotateTransition.setNode(root);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();
    }
    @Override
    boolean collision(Shape a)
    {
        for(int i=0;i<root.getChildren().size();i++)
        {
            Shape intersect = Shape.intersect((Shape) root.getChildren().get(i),a);
            if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) root.getChildren().get(i)).getFill().equals(a.getFill())))
            {
                return true;
            }
        }
        return false;
    }

}
class double_plus extends obstacle
{
    double_plus(int x,int y)
    {
        spawn(x,y);
    }

    @Override
    void spawn(int x, int y)
    {
        root=new Group();
        safe_distance=225;
        x_centre=x;
        y_centre=y;
        Rectangle rectangle = new Rectangle();
        rectangle.setX(x_centre-175);
        rectangle.setY(y_centre);
        rectangle.setHeight(20);
        rectangle.setWidth(200);
        rectangle.setFill(Color.rgb(250, 225, 0));
        Rotate rotate = new Rotate();
        rotate.setAngle(90);
        rotate.setPivotX(x_centre-175);
        rotate.setPivotY(y_centre);
        rectangle.getTransforms().addAll(rotate);

        Rectangle rectangle2= new Rectangle();
        rectangle2.setX(x_centre+20-175);
        rectangle2.setY(y_centre-10);
        rectangle2.setHeight(20);
        rectangle2.setWidth(200);
        rectangle2.setFill(Color.rgb(255, 1, 129));
        Rotate rotate2 = new Rotate();
        rotate2.setAngle(180);
        rotate2.setPivotX(x_centre-175);
        rotate2.setPivotY(y_centre);
        rectangle2.getTransforms().addAll(rotate2);

        Rectangle rectangle3= new Rectangle();
        rectangle3.setX(x_centre-175);
        rectangle3.setY(y_centre-10);
        rectangle3.setHeight(20);
        rectangle3.setWidth(200);
        rectangle3.setFill(Color.rgb(144, 13, 255));
        Rotate rotate3= new Rotate();
        rotate3.setAngle(0);
        rotate3.setPivotX(x_centre-175);
        rotate3.setPivotY(y_centre);
        rectangle3.getTransforms().addAll(rotate3);

        Rectangle rectangle4= new Rectangle();
        rectangle4.setX(x_centre-175);
        rectangle4.setY(y_centre-20);
        rectangle4.setHeight(20);
        rectangle4.setWidth(200);
        rectangle4.setFill(Color.rgb(50, 219, 240));
        Rotate rotate4 = new Rotate();
        rotate4.setAngle(-90);
        rotate4.setPivotX(x_centre-175);
        rotate4.setPivotY(y_centre);
        rectangle4.getTransforms().addAll(rotate4);

        Group root1=new Group(rectangle,rectangle2,rectangle3,rectangle4);

        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.millis(5000));
        rotateTransition.setNode(root1);
        rotateTransition.setByAngle(360);
        rotateTransition.setCycleCount(1000);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setInterpolator(Interpolator.LINEAR);
        rotateTransition.play();

        Rectangle rectangle11 = new Rectangle();
        rectangle11.setX(x_centre+175);
        rectangle11.setY(y_centre);
        rectangle11.setHeight(20);
        rectangle11.setWidth(200);
        rectangle11.setFill(Color.rgb(250, 225, 0));
        Rotate rotate11 = new Rotate();
        rotate11.setAngle(90);
        rotate11.setPivotX(x_centre+175);
        rotate11.setPivotY(y_centre);
        rectangle11.getTransforms().addAll(rotate11);

        Rectangle rectangle22= new Rectangle();
        rectangle22.setX(x_centre+20+175);
        rectangle22.setY(y_centre-10);
        rectangle22.setHeight(20);
        rectangle22.setWidth(200);
        rectangle22.setFill(Color.rgb(144, 13, 255));
        Rotate rotate22 = new Rotate();
        rotate22.setAngle(180);
        rotate22.setPivotX(x_centre+175);
        rotate22.setPivotY(y_centre);
        rectangle22.getTransforms().addAll(rotate22);

        Rectangle rectangle33= new Rectangle();
        rectangle33.setX(x_centre+175);
        rectangle33.setY(y_centre-10);
        rectangle33.setHeight(20);
        rectangle33.setWidth(200);
        rectangle33.setFill(Color.rgb(255, 1, 129));
        Rotate rotate33= new Rotate();
        rotate33.setAngle(0);
        rotate33.setPivotX(x_centre+175);
        rotate33.setPivotY(y_centre);
        rectangle33.getTransforms().addAll(rotate33);

        Rectangle rectangle44= new Rectangle();
        rectangle44.setX(x_centre+175);
        rectangle44.setY(y_centre-20);
        rectangle44.setHeight(20);
        rectangle44.setWidth(200);
        rectangle44.setFill(Color.rgb(50, 219, 240));
        Rotate rotate44 = new Rotate();
        rotate44.setAngle(-90);
        rotate44.setPivotX(x_centre+175);
        rotate44.setPivotY(y_centre);
        rectangle44.getTransforms().addAll(rotate44);

        Group root2=new Group(rectangle11,rectangle22,rectangle33,rectangle44);

        RotateTransition rotateTransition1 = new RotateTransition();
        rotateTransition1.setDuration(Duration.millis(5000));
        rotateTransition1.setNode(root2);
        rotateTransition1.setByAngle(-360);
        rotateTransition1.setCycleCount(1000);
        rotateTransition1.setAutoReverse(false);
        rotateTransition1.setInterpolator(Interpolator.LINEAR);
        rotateTransition1.play();

        root.getChildren().addAll(root1,root2);
    }

    @Override
    boolean collision(Shape a)
    {
        Group temp= (Group)root.getChildren().get(0);
        for(int i=0;i<temp.getChildren().size();i++)
        {
            Shape intersect = Shape.intersect((Shape) temp.getChildren().get(i),a);
            if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) temp.getChildren().get(i)).getFill().equals(a.getFill())))
            {
                return true;
            }
        }
        Group temp2= (Group)root.getChildren().get(1);
        for(int i=0;i< temp2.getChildren().size();i++)
        {
            Shape intersect = Shape.intersect((Shape) temp2.getChildren().get(i),a);
            if (intersect.getBoundsInLocal().getWidth() != -1 &&!( ((Shape) temp2.getChildren().get(i)).getFill().equals(a.getFill())))
            {
                return true;
            }
        }
        return false;
    }
}

