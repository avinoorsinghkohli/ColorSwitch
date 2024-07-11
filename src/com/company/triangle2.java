//package sample;
//
//import com.company.obstacle;
//import javafx.animation.AnimationTimer;
//import javafx.scene.Group;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Line;
//import javafx.scene.transform.Rotate;
//
//public class Triangle extends obstacle
//{
//    Group triangle;
//    public Triangle(double X, double Y, Color c1, Color c2, Color c3){
//        triangle = new Group();
//        Line line1 = new Line(X,Y+100, X-86.6,Y-50);
//        line1.setStroke(c1);
//        line1.setStrokeWidth(20);
//        Line line2 = new Line(X,Y+100, X+86.6,Y-50);
//        line2.setStroke(c2);
//        line2.setStrokeWidth(20);
//        Line line3 = new Line(X-86.6,Y-50, X+86.6,Y-50);
//        line3.setStroke(c3);
//        line3.setStrokeWidth(20);
//
//        triangle.getChildren().addAll(line1, line2, line3);
//
//        Rotate r = new Rotate();
//        triangle.getTransforms().add(r);
//        r.setPivotX(X);
//        r.setPivotY(Y);
//
//        AnimationTimer timer = new AnimationTimer() {
//            @Override
//            public void handle(long l) {
//                r.setAngle(r.getAngle()+2);
//            }
//        };
//        timer.start();
//    }
//
//    public Group getFigure() {
//        return triangle;
//    }
//
//    @Override
//    public void display() {
//
//    }
//
//    @Override
//    public String returnType() {
//        return "Triangle";
//    }
//}