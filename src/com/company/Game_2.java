package com.company;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.util.converter.IntegerStringConverter;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;


public class Game_2
{
    Label scorelabel;
    Label l2;
    Button btn;
    int score,index;
    Stage primaryStage;
    Arc ball;
    Rectangle ground;
    ArrayList<Rectangle> columns;
    int W=2080,H=1000;
    int ticks,ymotion;
    Group root;
    boolean gameOver;
    Label l;
    LinearGradient ld;
    IntegerStringConverter str;
    Timeline tim;
    Scene scene;
    int X,Y;
    ArrayList<ImageView> star_list=new ArrayList<>();
    star Star;
    boolean SoundOn=true;
    void addColumn()
    {
        int space=300;
        int width=100;
        int height=50+(int)(Math.random()*200);

        columns.add(new Rectangle(W+width+(columns.size()*200),H-height-120,width,height));
        columns.add(new Rectangle(W+width+(columns.size()-1)*200,0,width,H-height-space));
//        ImageView temp=Star.add_star();
//        star_list.add(temp);
//        root.getChildren().add(temp);
    }
    void Collision()
    {
        for(int i=0;i<columns.size();i++)
        {
            if(isCollided(columns.get(i)))
            {
                String path = "./src/dead.wav";
                Media media = new Media(new File(path).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(Main.Sound_game);
                gameOver=true;
                if(ball.getCenterX()<=columns.get(i).getX())
                    ball.setCenterX(columns.get(i).getX()-2*ball.getRadiusX()+10);
                else
                {
                    if(columns.get(i).getY()!=0)
                    {
                        ball.setCenterY(columns.get(i).getY()-2*ball.getRadiusY());
                    }
                    else if(ball.getCenterY()>columns.get(i).getHeight())
                    {
                        ball.setCenterY(columns.get(i).getHeight());
                    }
                }
            }
        }
        if(ball.getCenterY()>H-120||ball.getCenterY()<0)
        {
            gameOver=true;

        }

        if(gameOver)
        {

            ball.setCenterY(H-120-ball.getRadiusY());

            l.setText("GameOver\n   Score:"+str.toString(score));
            l.setFont(new Font("Arial",50));
            l.setLayoutX(primaryStage.getWidth()/2-130);
            l.setLayoutY(primaryStage.getHeight()/2-50);
            l.setTextFill(Color.ORANGE);
        }
    }

    boolean isCollided(Shape a)
    {
        Shape intersect = Shape.intersect(a,ball);
        if (intersect.getBoundsInLocal().getWidth() != -1)
        {
            return true;
        }
        return false;
    }
    void Jump()
    {

        if(!gameOver)
        {
            String path = "./src/jump.wav";
            Media media = new Media(new File(path).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setAutoPlay(Main.Sound_game);
            if(ymotion>0)
            {
                ymotion=0;
            }
            ymotion=ymotion-10;
        }

    }
    void start1()
    {

        ball.setCenterX(W/2-10);
        ball.setCenterY(H/2-10);
        gameOver=false;
        ymotion=0;
        score=0;
        scorelabel.setText("Score"+str.toString(score));
        root.getChildren().remove(btn);
        root.getChildren().removeAll(columns);
        columns.clear();
        int i=0;
        while(i<30)
        {
            addColumn();
            i++;
        }
        tim.pause();
        scene.setOnKeyReleased(k ->
        {
            String code = k.getCode().toString();
            if(code.equals("UP"))
            {

                root.getChildren().addAll(columns);
                tim.play();

            }
        });
    }

    public void start()
    {
        primaryStage=Main.stage;
        primaryStage.setHeight(H);
        primaryStage.setWidth(W);
        primaryStage.setResizable(false);

        root=new Group();
        DropShadow ds1 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(4.0f);
        ds1.setColor(Color.GREY);
        DropShadow ds2 = new DropShadow();
        ds1.setOffsetY(4.0f);
        ds1.setOffsetX(4.0f);
        ds1.setColor(Color.BLACK);


        ball=new Arc(W/2-10,H/2-10,15,15,0,360);
        color_changer.assign_color(ball);

        index=0;
        ymotion=0;

        str=new IntegerStringConverter();

        l=new Label();
        l2=new Label();
        scorelabel=new Label();
        //scorelabel.setText("Score"+str.toString(score));
        scorelabel.setFont(new Font("Arial",20));

        ld=new LinearGradient(0.0, 0.0, 1.0, 0.0, true,
                CycleMethod.NO_CYCLE,
                new Stop(0.0, Color.GREY),
                new Stop(1.0, Color.BLACK));

        columns=new ArrayList<>();

//        ground=new Rectangle(0,H-120,W,120);
//        ground.setFill(Color.);

        tim=new Timeline();
        tim.setCycleCount(Animation.INDEFINITE);


        gameOver=false;

        btn = new Button();
        btn.setText("Restart");
        btn.setTranslateX(350);
        btn.setTranslateY(600);
        btn.setPrefSize(100,50);
        btn.setTextFill(Color.BLUE);
        btn.setFont(new Font("Arial",20));
        btn.setEffect(ds2);

        KeyFrame kf=new KeyFrame(Duration.millis(20),e ->
        {

            ticks++;
            if(ticks%2==0&&ymotion<15)
            {
                ymotion=ymotion+2;

            }
            X=X-2;
            int y=(int)ball.getCenterY()+ymotion;
            ball.setCenterY(y);
            scene.setOnKeyReleased(k ->
            {
                String code = k.getCode().toString();
                if(code.equals("UP"))
                {
                    Jump();
                }
            });
            Collision();
            if(gameOver)
            {
                tim.pause();
                HBox pauseRoot = new HBox(20);
                pauseRoot.getChildren().add(new Label());
                pauseRoot.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
                pauseRoot.setAlignment(Pos.CENTER);
                pauseRoot.setPadding(new Insets(20));
                ImageView resume_icon = new ImageView(new Image("resume.png"));
                resume_icon.setPreserveRatio(true);
                resume_icon.setFitHeight(80);
                Button resume = new Button("",resume_icon);
                ImageView exit_icon = new ImageView(new Image("exit.png"));
                exit_icon.setPreserveRatio(true);
                exit_icon.setFitHeight(80);
                Button exit = new Button("",exit_icon);
                pauseRoot.getChildren().add(resume);
                pauseRoot.getChildren().add(exit);
                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(Main.stage);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
                pauseRoot.getChildren().add(l);
                resume.setOnAction(event ->
                {
                    start1();
                    popupStage.close();
                });
                exit.setOnAction(event ->
                {
                    MainMenu obj_display=new MainMenu();
                    Game game=new Game();
                    try
                    {
                        game.game_prev();
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                    Game.scene_start=false;
                    Scene scene2 = null;
                    try
                    {
                        Game_2 game2=new Game_2();
                        game2.start();
                        scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 400, 600);
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                    scene2.getStylesheets().add("styles.css");
                    Main.stage.setTitle("Main menu");
                    Main.stage.setScene(scene2);
                    Main.stage.show();
                    popupStage.close();
                });
                popupStage.show();
            }
        });

        KeyFrame kf2 = new KeyFrame(Duration.millis(20),e->
        {

            for (Rectangle column : columns)
            {
                color_changer.assign_color(column);
                column.setEffect(ds1);
                column.setX((column.getX() - 5));

                if (column.getY() == 0 && ball.getCenterX() + ball.getRadiusX() > column.getX() + column.getWidth() / 2 - 5 && ball.getCenterX() + ball.getRadiusX() < column.getX() + column.getWidth() / 2 + 5)
                {
                    score++;
                    scorelabel.setText("Score:" + str.toString(score));
                    scorelabel.setTextFill(Color.DARKBLUE);
                }

            }

            for(int i=0;i<columns.size();i++)
            {

                Rectangle column=columns.get(i);

                if((column.getX()+column.getWidth())<0)
                {
                    columns.remove(i);
                }
            }

        });

        tim.getKeyFrames().addAll(kf,kf2);
        root.getChildren().addAll(scorelabel);
       // root.getChildren().add(ground);
        root.getChildren().add(ball);

        scene=new Scene(root);
        scene.setFill(Color.BLACK);
        start1();

        primaryStage.setScene(scene);
        primaryStage.setHeight(H);
        primaryStage.setWidth(W);
        primaryStage.show();

    }


}