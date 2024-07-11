package com.company;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Main extends Application
{
    public static Stage stage;
    public static boolean Sound_bg=true;
    public static boolean Sound_game=true;
    public static MediaPlayer mediaPlayer12;
    public static Media media1;

    @Override
    public void start(Stage stage1) throws IOException
    {

        stage=stage1;
        int width = 400;
        int height = 600;
        String path1 = "./src/background.wav";
       media1 = new Media(new File(path1).toURI().toString());
       mediaPlayer12 = new MediaPlayer(media1);

        Group root=new Group();
        Arc ball = new Arc(950,750,15,15,0,360);
        ball.setFill(Color.WHITE);
        TranslateTransition trans_temp=new TranslateTransition();
        TranslateTransition trans0=new TranslateTransition();
        TranslateTransition trans1=new TranslateTransition();
        TranslateTransition trans2=new TranslateTransition();
        TranslateTransition trans3=new TranslateTransition();
        TranslateTransition trans4=new TranslateTransition();

        trans_temp.setToY(0);
        trans_temp.setDuration(Duration.millis(1000));
        trans_temp.setNode(ball);

        trans0.setToY(-100);
        trans0.setDuration(Duration.millis(600));
        trans0.setNode(ball);

        trans1.setToY(100);
        trans1.setDuration(Duration.millis(400));
        trans1.setNode(ball);

        trans2.setToY(-100);
        trans2.setDuration(Duration.millis(600));
        trans2.setNode(ball);

        trans3.setToY(100);
        trans3.setDuration(Duration.millis(400));
        trans3.setNode(ball);

        trans4.setToY(-350);
        trans4.setDuration(Duration.millis(800));
        trans4.setNode(ball);
        int x=50;
        int y=50;
        Label l1 = new Label();
        l1.setText("C");
        l1.setFont(new Font("Arial", 60));
        l1.setTranslateX(780+x);
        l1.setTranslateY(95);
        l1.setTextFill(Color.WHITE);
        root.getChildren().add(l1);
        Label l2 = new Label();
        l2.setText("L");
        l2.setFont(new Font("Arial", 60));
        l2.setTranslateX(875+x);
        l2.setTranslateY(95);
        l2.setTextFill(Color.WHITE);
        root.getChildren().add(l2);
        circle_spawn cir_o1=new circle_spawn(850+x,130,25,15);
        circle_spawn cir_o2=new circle_spawn(935+x,130,25,15);
        root.getChildren().addAll(cir_o1.root,cir_o2.root);
        Label l3 = new Label();
        l3.setText("R");
        l3.setFont(new Font("Arial", 60));
        l3.setTranslateX(960+x);
        l3.setTranslateY(95);
        l3.setTextFill(Color.WHITE);
        root.getChildren().add(l3);
        Label l4= new Label();
        l4.setText("SWITCH");
        l4.setFont(new Font("Arial", 60));
        l4.setTranslateX(830);
        l4.setTranslateY(95+y);
        l4.setTextFill(Color.WHITE);
        root.getChildren().add(l4);
        circle cir=new circle(950,425);
        circle_spawn cir1=new circle_spawn(950,425,135,110,Color.rgb(50, 219, 240),Color.rgb(144, 13, 255),Color.rgb(255, 1, 129),Color.rgb(250, 225, 0),true);
        circle_spawn cir2=new circle_spawn(950,425,95,70,Color.rgb(255, 1, 129),Color.rgb(50, 219, 240),Color.rgb(250, 225, 0),Color.rgb(144, 13, 255),false);
        root.getChildren().addAll(cir.root,cir1.root,cir2.root);
        root.getChildren().add(ball);
        FadeTransition fade=new FadeTransition();
        fade.setDuration(Duration.millis(1500));
        fade.setFromValue(10);
        fade.setToValue(0.1);
        fade.setCycleCount(1);
        fade.setNode(root);


        SequentialTransition seqT = new SequentialTransition (trans_temp,trans0,trans1,trans2,trans3,trans4,fade);
        seqT.play();
        Scene scene_pre=new Scene(root,2080,1000);
        scene_pre.setFill(Color.BLACK);
        MainMenu obj_display=new MainMenu();
        Game game=new Game();
        game.game_prev();
        Game_2 game2=new Game_2();
        game2.start();
        Scene scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 2080, 1000);
        scene2.getStylesheets().add("styles.css");
        stage.setTitle("Main menu");
        EventHandler<ActionEvent> main = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {

                stage.setScene(scene2);
            }
        };

        seqT.setOnFinished(main);
       stage.setScene(scene_pre);
       stage.show();


    }
    int three()
    {

        return 300;
    }
    public static void main(String[] args)
    {
        launch(args);
    }
}
//--module-path "/home/avinoor/javafx-sdk-11.0.2/lib" --add-modules=javafx.controls,javafx.fxml