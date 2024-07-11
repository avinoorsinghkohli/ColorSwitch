package com.company;

import javafx.animation.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Game implements Serializable
{
    static boolean scene_start = false;
    transient Scene scene;
    private int xyz;
    private int op;
    private double qswd;
    private boolean ty;
    protected double y;
    final private double save_file_new;
    //Ball b;
    transient Group root;
    ArrayList<obstacle> obstacle;
    ArrayList<star> star;
    ArrayList<color_changer> color_changer_list;
    transient Arc ball;
    double ball_x_centre=0;
    double ball_y_centre=0;
    transient Paint ball_color;
    int points = 0;
    double x = 0;
    static int difficult = 0;
    transient Timeline tl;
    transient MediaPlayer mediaPlayer;
    transient MediaPlayer mediaPlayer12;
    transient saved_games save;
    transient Media media1;
    Game currentGame = this;
    transient ImageView start;
    double start_x,start_y;
    int [] temp=new int[1];
    Game()
    {
        //b=new Ball();
        star = new ArrayList<>();
        color_changer_list = new ArrayList<>();
        save_file_new=0;
    }

    public void game_prev() throws IOException
    {
        root = new Group();
        ball = new Arc(950f, 850f, 15f, 15f, 0, 360);
        ball.setCenterX(950);
        ball.setCenterY(850);
        com.company.color_changer.assign_color(ball);
        root.getChildren().add(ball);
        obstacle = new ArrayList<>();
        spawn_obstacle();
        start_x=890;
        start_y=875;
        game();
    }

    public void game() throws IOException
    {
        //isCollide(ball1, root);
        //adding a color changer
        //adding a pause button
//        highest_Score temp456=new highest_Score();
//        temp456.edit(0);
//

        start = new ImageView(new Image("103844015-.jpg"));
        start.setFitHeight(125);
        start.setPreserveRatio(true);
        start.setX(start_x);
        start.setY(start_y);
        root.getChildren().add(start);
        ImageView view = new ImageView(new Image("images.png"));
        view.setFitHeight(80);
        view.setPreserveRatio(true);
        Button button21 = new Button();
        button21.setTranslateX(1720);
        button21.setTranslateY(10);
        button21.setPrefSize(80, 80);
        button21.setGraphic(view);

        //adding a score label
        Label l1 = new Label();
        l1.setText(Integer.toString(points));
        root.getChildren().add(l1);
        l1.setFont(new Font("Arial", 40));
        l1.setTranslateX(40);
        l1.setTranslateY(5);
        l1.setTextFill(Color.WHITE);
        ImageView star12 = new ImageView(new Image("star.jpg"));
        star12.setFitHeight(40);
        star12.setPreserveRatio(true);
        star12.setX(0);
        star12.setY(10);
        root.getChildren().add(star12);
        scene = new Scene(root, 2080, 1000);
        tl = new Timeline();
        tl.setCycleCount(Animation.INDEFINITE);

        AtomicBoolean game_start = new AtomicBoolean(false);
        //AtomicBoolean cont= new AtomicBoolean(false);
        KeyFrame kf = new KeyFrame(Duration.millis(10), e ->
        {

            ball_x_centre=ball.getCenterX();
            ball_y_centre=ball.getCenterY();
            ball_color=ball.getFill();
            scene.setOnKeyReleased(k ->
            {
                String code = k.getCode().toString();
                if (code.equals("UP"))
                {
                    jump();

                }
            });
            for (int i=0;i<obstacle.size();i++)
            {
                Paint a=ball.getFill();
                double x_local=ball.getCenterX();
                double y_local=ball.getCenterY();
                if(obstacle.get(i).collision(ball))
                {

                    tl.pause();
                    ball.setFill(Color.BLACK);
                    String path = "./src/dead.wav";
                    Media media = new Media(new File(path).toURI().toString());
                    mediaPlayer = new MediaPlayer(media);
                    mediaPlayer.setAutoPlay(Main.Sound_game);
                    ArrayList<ball1> pieces = new ArrayList<>();
                    String[] color = new String[]{"#FAE100","#900DFF","#FF0181","#32DBF0"};
                    Random rand = new Random();
                    ParallelTransition temptransition = new ParallelTransition();
                    for(int b=0;b<100;++b)
                    {
                        Path element = new Path(new MoveTo(x_local, y_local));
                        Arc temp = new Arc(x_local, y_local, 3, 3, 0, 360);
                        ball1 temp1 = new ball1();
                        int ind = rand.nextInt(4);
                        temp.setFill(Color.web(color[ind]));
                        temp1.test = temp;
                        pieces.add(temp1);
                        root.getChildren().add(temp);
                        int no1 = -10;
                        int no2 = 2090;
                        int no3 = rand.nextInt(2090);
                        int[] rnd = new int[]{no1, no2};
                        if (b % 2 == 0)
                            element.getElements().add(new LineTo(rnd[rand.nextInt(2)], no3));
                        else
                            element.getElements().add(new LineTo(no3, rnd[rand.nextInt(2)]));

                        PathTransition trans = new PathTransition();

                        trans.setDuration(Duration.millis(1000 + rand.nextInt(600)));

                        trans.setNode(temp1.test);

                        trans.setPath(element);
                        temptransition.getChildren().add(trans);
                    }

                        temptransition.play();
                        int tempo=i;
                        EventHandler<ActionEvent> revive_event = actionEvent -> revive(obstacle.get(tempo).y_centre,obstacle.get(tempo).safe_distance,a,y_local);
                        total_points temp123456 = null;
                    try
                    {
                        temp123456=new total_points();
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                    if(temp123456.points>=5)
                        temptransition.setOnFinished(revive_event);
                        else
                        {
                            try
                            {
                                highest_Score temp456=new highest_Score();
                                temp456.edit(points);
                            }
                            catch (IOException e5)
                            {
                                e5.printStackTrace();
                            }
                            try
                            {
                                total_points temp123=new total_points();
                                temp123.edit(points);
                                //System.out.println(temp123.points+points+"/><");
                            }
                            catch (IOException ioException)
                            {
                                ioException.printStackTrace();
                            }
                            MainMenu obj_display=new MainMenu();
                            Game game=new Game();
                            try {
                                game.game_prev();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                            scene_start=false;
                            Scene scene2 = null;
                            try {
                                Game_2 game2=new Game_2();
                                game2.start();
                                scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 400, 600);
                            } catch (IOException ioException) {
                                ioException.printStackTrace();
                            }
                            scene2.getStylesheets().add("styles.css");
                            Main.stage.setTitle("Main menu");
                            Main.stage.setScene(scene2);
                            Main.stage.show();

                        }
                        break;
                }
                if(obstacle.get(i).y_centre<0)
                    break;
            }
            if (com.company.star.isCollide(ball,star.get(0).star12))
            {
                String path = "./src/star.wav";
                Media media = new Media(new File(path).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(Main.Sound_game);
                root.getChildren().remove(star.get(0).star12);
                star.remove(0);
                points++;
                l1.setText(Integer.toString(points));
            }
//            if(ball.getCenterY()>1100)
//            {
//                tl.pause();
//                revive();
//            }
            if (com.company.color_changer.isCollide(ball,color_changer_list.get(0).root))
            {
                String path = "./src/colorswitch.wav";
                Media media = new Media(new File(path).toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(Main.Sound_game);
                root.getChildren().remove(color_changer_list.get(0).root);
                color_changer_list.remove(0);
                color_changer.assign_color(ball);
                ball_color=ball.getFill();
            }

            //ball.setCenterY(ball.getCenterY()+10);
            ball.setCenterY(ball.getCenterY()+0.3+x);
            ball_x_centre=ball.getCenterX();
            ball_y_centre=ball.getCenterY();
            x += 0.15;
            //System.out.println(x);
            setDifficulty();
            Bounds bound = ball.localToScreen(ball.getBoundsInLocal());

            if (scene_start && bound.getCenterY() < 750)
            {
                for (com.company.obstacle value : obstacle)
                {
                    value.root.setTranslateY(value.root.getTranslateY() + 2);
                    value.y_centre = value.y_centre + 2;
                   // System.out.println(value.y_centre);
                }
                for (star imageView : star)
                {
                    imageView.star12.setTranslateY(imageView.star12.getTranslateY() + 2);
                    imageView.y_centre=imageView.y_centre+2;
                }
                for (color_changer group : color_changer_list)
                {
                    group.root.setTranslateY(group.root.getTranslateY() + 2);
                    group.y_centre=group.y_centre+2;
                }
                start.setTranslateY(start.getTranslateY()+2);
                start_y=start_y+2;
            }

        });
        tl.getKeyFrames().add(kf);
        scene.setOnKeyReleased(k ->
        {
            String code = k.getCode().toString();
            if (code.equals("UP"))
            {
                jump();
                tl.play();
            }
        });
        scene.setFill(Color.BLACK);
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                tl.pause();
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
                ImageView save_icon = new ImageView(new Image("save.png"));
                save_icon.setPreserveRatio(true);
                save_icon.setFitHeight(80);
                Button save_game = new Button("",save_icon);
                pauseRoot.getChildren().add(resume);
                pauseRoot.getChildren().add(exit);
                pauseRoot.getChildren().add(save_game);
                Stage popupStage = new Stage(StageStyle.TRANSPARENT);
                popupStage.initOwner(Main.stage);
                popupStage.initModality(Modality.APPLICATION_MODAL);
                popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));
                save=new saved_games();
                resume.setOnAction(event ->
                {
                    scene.setOnKeyReleased(k ->
                    {
                        String code = k.getCode().toString();
                        if (code.equals("UP"))
                        {
                            jump();
                            tl.play();
                        }
                    });
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
                    try
                    {
                        total_points temp123=new total_points();
                        temp123.edit(points);
                        //System.out.println(temp123.points+points+"/><");
                    }
                    catch (IOException ioException)
                    {
                        ioException.printStackTrace();
                    }
                    try
                    {
                        highest_Score temp456=new highest_Score();
                        temp456.edit(points);
                    }
                    catch (IOException e1)
                    {
                        e1.printStackTrace();
                    }
                    scene_start=false;
                    Scene scene2 = null;
                    try {
                        Game_2 game2=new Game_2();
                        game2.start();
                        scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 400, 600);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    scene2.getStylesheets().add("styles.css");
                    Main.stage.setTitle("Main menu");
                    Main.stage.setScene(scene2);
                    Main.stage.show();
                    popupStage.close();
                });
                save_game.setOnAction(event ->
                {
                    VBox pauseRoot1 = new VBox(100);
                    pauseRoot1.setMinWidth(100);
//                    FadeTransition fade=new FadeTransition();
//                    fade.setDuration(Duration.millis(1500));
//                    fade.setFromValue(10);
//                    fade.setToValue(0.1);
//                    fade.setCycleCount(1);
//                    fade.setNode(pauseRoot1);
                    pauseRoot1.setOpacity(100);
                    pauseRoot1.setStyle("-fx-background-color: rgba(0,0,0, 0.8);");
                    pauseRoot1.setAlignment(Pos.CENTER);
                    pauseRoot1.setPadding(new Insets(20));
                    pauseRoot.setEffect(new GaussianBlur());
                    Label l4=new Label("Enter Game Id: ");
                    pauseRoot1.getChildren().add(l4);
                    l4.setFont(new Font("Arial", 30));
                    l4.setTextFill(Color.WHITE);
                    TextField b = new TextField();
                    pauseRoot1.getChildren().add(b);
                    ImageView revive_icon1 = new ImageView(new Image("tick.jpg"));
                    revive_icon1.setPreserveRatio(true);
                    revive_icon1.setFitHeight(60);
                    Button revive1 = new Button("",revive_icon1);
                    //Button exit = new Button("Exit");
                    ImageView exit_icon1 = new ImageView(new Image("cross.png"));
                    exit_icon1.setPreserveRatio(true);
                    exit_icon1.setFitHeight(60);
                    Button exit1 = new Button("",exit_icon1);
                    pauseRoot1.getChildren().add(revive1);
                    pauseRoot1.getChildren().add(exit1);
                    Stage popupStage1 = new Stage(StageStyle.TRANSPARENT);
                    popupStage1.initOwner(Main.stage);
                    popupStage1.initModality(Modality.APPLICATION_MODAL);
                    popupStage1.setScene(new Scene(pauseRoot1, Color.BLACK));

                    revive1.setOnAction(event1->
                    {
                        try
                        { currentGame=Game.this;
                            save.serialize(currentGame,b.getText());
                        } catch (IOException ioException)
                        {
                            ioException.printStackTrace();
                        }
                        pauseRoot.setEffect(null);
                        popupStage1.close();

                    });
                    exit1.setOnAction(event2->
                    {
                        pauseRoot.setEffect(null);
                        popupStage1.close();
                    });
                    popupStage1.show();
                });
                popupStage.show();
            }
        };

        button21.setOnAction(event);
        root.getChildren().add(button21);
    }
    void jump()
    {
        //ball.setCenterY(ball.getCenterY()-150);

        String path = "./src/jump.wav";
        Media media = new Media(new File(path).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(Main.Sound_game);
        x = 0;
        KeyFrame kf3 = new KeyFrame(Duration.millis(5), e->{
            ball.setCenterY(ball.getCenterY()-2-x);
            x -= 0.25;
        });
        ball_x_centre=ball.getCenterX();
        ball_y_centre=ball.getCenterY();
        Timeline vela = new Timeline();
        vela.getKeyFrames().add(kf3);
        vela.setCycleCount(20);
        vela.play();
    }
    void spawn_obstacle()
    {
        int i=0;
        int y=400;
        Random random=new Random();
        while(i<20)
        {
            int temp=0;
            if(i<4)
            temp= random.nextInt(2)+1;
            if(4<=i && i<6)
                temp= random.nextInt(3)+1;
            if(6<=i)
                temp= random.nextInt(5)+1;
            if(temp==1)
            {
                circle c=new circle(950,y);
                root.getChildren().add(c.root);
                obstacle.add(c);
                spawn_star(950,y);
                spawn_color_changer(950,y-350);
                y=y-700;
            }
            if(temp==2)
            {
                plus pl=new plus(800,y);
                root.getChildren().add(pl.root);
                obstacle.add(pl);
                spawn_star(950,y-220);
                spawn_color_changer(950,y-375);
                y=y-700;
            }
            if(temp==3)
            {

                double_circle dc=new double_circle(950,y);
                root.getChildren().add(dc.root);
                obstacle.add(dc);
                spawn_star(950,y-100);
                spawn_color_changer(950,y-350);
                y=y-700;
            }
            if(temp==4)
            {
                y=y-150;
                up_circle uc=new up_circle(950,y);
                root.getChildren().add(uc.root);
                obstacle.add(uc);
                spawn_star(950,y+150);
                spawn_star(950,y-150);
                spawn_color_changer(950,y-525);
                y=y-150;
                y=y-700;
            }

            if(temp==5)
            {
                double_plus dl=new double_plus(975,y);
                root.getChildren().add(dl.root);
                obstacle.add(dl);
                spawn_star(950,y);
                spawn_color_changer(950,y-375);
                y=y-700;
            }
            i++;
        }
    }
    void spawn_star(int x,int y)
    {
        star Star=new star();
        ImageView a;
        a=Star.add_star(x-20,y-10);
        root.getChildren().add(a);
        star.add(Star);
    }
    void spawn_color_changer(int x,int y)
    {
        color_changer obj=new color_changer();
        obj.spawn(x,y);
        root.getChildren().add(obj.root);
        color_changer_list.add(obj);
    }
    void setDifficulty()
    {
        if (points%5==0 && points!=0)
        {
            difficult++;
        }
    }
    public void revive(double y,int safe,Paint a,double y_local)
    {
        HBox pauseRoot = new HBox(50);
        FadeTransition fade=new FadeTransition();
        fade.setDuration(Duration.millis(1500));
        fade.setFromValue(10);
        fade.setToValue(0.1);
        fade.setCycleCount(1);
        fade.setNode(pauseRoot);
        pauseRoot.setPrefHeight(50);
        pauseRoot.setStyle("-fx-background-color: rgba(224, 94, 0, 1);");
        pauseRoot.setAlignment(Pos.CENTER);
        pauseRoot.setPadding(new Insets(20));
        ImageView revive_icon = new ImageView(new Image("revive1.jpeg"));
        revive_icon.setPreserveRatio(true);
        revive_icon.setFitHeight(80);
        Button revive = new Button("",revive_icon);
        //Button exit = new Button("Exit");
        ImageView exit_icon = new ImageView(new Image("exit.png"));
        exit_icon.setPreserveRatio(true);
        exit_icon.setFitHeight(80);
        Button exit = new Button("",exit_icon);
        pauseRoot.getChildren().add(revive);
        pauseRoot.getChildren().add(exit);
        Stage popupStage = new Stage(StageStyle.TRANSPARENT);
        popupStage.initOwner(Main.stage);
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setScene(new Scene(pauseRoot, Color.TRANSPARENT));

        revive.setOnAction(event ->
        {
            try
            {
                total_points temp123=new total_points();
                temp123.edit(-5);
                System.out.println(temp123.points+points+"><");
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            if(y_local>y)
                ball.setCenterY(y+safe);
            else
                ball.setCenterY(y-safe);
            ball_x_centre=ball.getCenterX();
            ball_y_centre=ball.getCenterY();
            popupStage.close();
            Random rand = new Random();
            ParallelTransition temptransition1 = new ParallelTransition();
            ArrayList<ball1> pieces1 = new ArrayList<>();
            for(int i=0;i<100;++i){
                Path element = new Path();
                Arc temp = new Arc(ball.getCenterX(),ball.getCenterY(),3,3,0,360);
                ball1 temp1= new ball1();
                color_changer.assign_color(temp);
                temp1.test = temp;
                pieces1.add(temp1);
                root.getChildren().add(temp);
                int no1 = -10;
                int no2 = 2090;
                int no3 = rand.nextInt(2090);
                int[] rnd = new int[]{no1,no2};
                MoveTo temp2 = new MoveTo();
                element.getElements().add(temp2);
                if(i%2==0)
                {
                    temp2.setX(rnd[rand.nextInt(2)]);
                    temp2.setY(no3);
                }
                else
                    {
                    temp2.setY(rnd[rand.nextInt(2)]);
                    temp2.setX(no3);
                    }
                element.getElements().add(new LineTo(ball.getCenterX(), ball.getCenterY()));
                PathTransition trans = new PathTransition();
                trans.setDuration(Duration.millis(1000+rand.nextInt(600)));
                trans.setNode(temp1.test);
                trans.setPath(element);
                temptransition1.getChildren().add(trans);
            }
            temptransition1.play();

            EventHandler<ActionEvent> punar_janam = actionEvent ->
            {

                for(ball1 l:pieces1)
                {
                    root.getChildren().remove(l.test);
                }

                ball.setFill(a);
                scene.setOnKeyReleased(k ->
                {
                    String code = k.getCode().toString();
                    if (code.equals("UP"))
                    {
                        jump();
                        tl.play();
                    }
                });
            };
            temptransition1.setOnFinished(punar_janam);

        });
        exit.setOnAction(event ->
        {
            try
            {
                highest_Score temp456=new highest_Score();
                temp456.edit(points);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            try
            {
                total_points temp123=new total_points();
                temp123.edit(points);
                //System.out.println(temp123.points+points+"/><");
            }
            catch (IOException ioException)
            {
                ioException.printStackTrace();
            }
            MainMenu obj_display=new MainMenu();
            Game game=new Game();
            try {
                game.game_prev();
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene_start=false;
            Scene scene2 = null;
            try {
                Game_2 game2=new Game_2();
                game2.start();
                scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 400, 600);
            } catch (IOException e) {
                e.printStackTrace();
            }
            scene2.getStylesheets().add("styles.css");
            Main.stage.setTitle("Main menu");
            Main.stage.setScene(scene2);
            Main.stage.show();
            popupStage.close();
        });
        popupStage.show();
    }
    public Scene setup() throws IOException {
        root=new Group();
        ball=new Arc(ball_x_centre,ball_y_centre,15,15,0,360);
        color_changer.assign_color(ball);
        root.getChildren().add(ball);
        for(int i=0;i<this.obstacle.size();i++)
        {
            obstacle.get(i).spawn(obstacle.get(i).x_centre,obstacle.get(i).y_centre);
            root.getChildren().add(obstacle.get(i).root);
        }
        for(int i=0;i<this.star.size();i++)
        {
            root.getChildren().add(star.get(i).add_star(star.get(i).x_centre,star.get(i).y_centre));
        }
        for(int i=0;i<this.color_changer_list.size();i++)
        {
            color_changer_list.get(i).spawn(color_changer_list.get(i).x_centre,color_changer_list.get(i).y_centre);
            root.getChildren().add(color_changer_list.get(i).root);
        }
        //scene=new Scene(root,2080,1000);
        //scene.setFill(Color.BLACK);
        scene_start=true;
        game();
        return scene;
    }

}



