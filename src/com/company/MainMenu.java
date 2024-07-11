package com.company;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class MainMenu
{
    public MainMenu()
    {

    }
    public GridPane main_screen(Scene scene,Scene scene2) throws IOException
    {
        Main.mediaPlayer12.setAutoPlay(Main.Sound_bg);
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        HBox modes = new HBox();
        modes.setSpacing(20);
        Button button1 = new Button("SAVED GAMES");
        button1.setId("modes");
        button1.setPrefSize(380, 63);
        Font font1 = Font.font("Montserrat", FontWeight.BOLD, 30);
        button1.setFont(font1);
        Image star = new Image("star.jpg");
        ImageView star1 = new ImageView(star);
        star1.setFitHeight(40);
        star1.setFitWidth(40);
        ImageView star2 = new ImageView(star);
        star2.setFitHeight(40);
        star2.setFitWidth(40);
        modes.getChildren().addAll(star1, button1, star2);
        modes.setAlignment(Pos.CENTER);
        saved_games_screes s=new saved_games_screes();
        EventHandler<ActionEvent> save = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                File[] files = new File("./saved_games").listFiles();
                for (File file : files)
                {
                    if (file.getName().contains("a_"))
                    {
                        String[] words = file.getName().split("_");
                        String temp=words[1];
                        //System.out.println(temp+"**");
                        s.add(temp.substring(0,temp.length()-4));
                    }
                }
                s.load_list();

            }
        };
        button1.setOnAction(save);
        ImageView playimage = new ImageView(
                new Image("playbutton.png")
        );
        ImageView playimage1 = new ImageView(
                new Image("playbutton1.jpg")
        );
        playimage.setFitHeight(200);
        playimage.setFitWidth(200);
        playimage1.setFitHeight(135);
        playimage1.setFitWidth(135);
        Button button2 = new Button("", playimage);
        button2.setPrefSize(130,130);
        button2.setId("play");
        button2.setPrefSize(130, 130);
        button2.setLayoutX(100);
        Button button7 = new Button("", playimage1);
        button7.setId("button7");
        button7.setLayoutX(555);
        button7.setPrefSize(208,208);
        Pane play = new Pane();
        circle_spawn c1=new circle_spawn(1108-900,103,125,110);
        circle_spawn c2=new circle_spawn(1108-900,103,145,130,Color.rgb(250, 225, 0),Color.rgb(255, 1, 129),Color.rgb(144, 13, 255),Color.rgb(50, 219, 240),true);
        circle_spawn c3=new circle_spawn(1108-900+450,105,125,110);
        circle_spawn c4=new circle_spawn(1108-900+450,105,145,130,Color.rgb(250, 225, 0),Color.rgb(255, 1, 129),Color.rgb(144, 13, 255),Color.rgb(50, 219, 240),true);
        play.getChildren().addAll(c1.root,button2,c2.root,button7,c3.root,c4.root);

        EventHandler<ActionEvent> playevent = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Game.scene_start=true;
                Main.stage.setScene(scene);
            }
        };
        EventHandler<ActionEvent> playevent2 = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Game.scene_start=true;
                Main.stage.setScene(scene2);
            }
        };
        button7.setOnAction(playevent2);
        //stage.setScene(scene);
        button2.setOnAction(playevent);

        button2.setOnAction(playevent);
        Button button3 = new Button("HELP");
        button3.setPrefSize(350, 58);

        button3.setId("button3");
        button3.setFont(font1);
        Button button4 = new Button("HIGH SCORE");
        button4.setPrefSize(320, 53);
        button4.setId("button4");
        button4.setFont(font1);
        total_points temp12=new total_points();
        highest_Score temp1234=new highest_Score();
        EventHandler<ActionEvent> scoreevent = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                int width = 2080;
                int height = 1000;
                Main.stage.setTitle("help");
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                Text heading = new Text("HIGHEST SCORE");
                Label highestscore = new Label();
                highestscore.setText(Integer.toString(temp1234.score));
                highestscore.setFont(new Font("Arial", 40));
                highestscore.setTextFill(Color.WHITE);
                Button back = new Button("");
                back.setId("back");
                back.setText("RETURN");
                back.setFont(font1);
                EventHandler<ActionEvent> returnevent = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent)
                    {
                        MainMenu obj_display=new MainMenu();
                        Game game=new Game();
                        try {
                            game.game_prev();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Game_2 game2=new Game_2();
                        game2.start();
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 2080, 1000);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        scene2.getStylesheets().add("styles.css");
                        Main.stage.setTitle("Main menu");
                        Main.stage.setScene(scene2);
                    }
                };
                back.setOnAction(returnevent);
                HBox row0 =new HBox();
                row0.setAlignment(Pos.BASELINE_LEFT);
                row0.getChildren().add(back);
                back.setPrefSize(350, 58);
                heading.setId("scoreheading");
                Text stars = new Text("STARS");
                stars.setId("scorestars");
                Label totalstars = new Label();
                totalstars.setText(Integer.toString(temp12.points));
                totalstars.setFont(new Font("Arial", 40));
                totalstars.setTextFill(Color.WHITE);
                HBox row1 = new HBox();
                row1.setAlignment(Pos.CENTER);
                row1.getChildren().add(heading);
                HBox row2 = new HBox();
                row2.setAlignment(Pos.CENTER);

                ImageView medal = new ImageView(
                    new Image("medal.png")
                );
                medal.setFitHeight(60);
                medal.setFitWidth(60);
                row2.setSpacing(50);
                row2.getChildren().addAll(highestscore,medal);
                HBox row3 = new HBox();
                row3.setAlignment(Pos.CENTER);
                ImageView stars2 = new ImageView(
                        new Image("star.jpg")
                );

                stars2.setFitHeight(50);
                stars2.setFitWidth(50);
                row3.setSpacing(50);
                row3.getChildren().addAll(totalstars,stars2);
                HBox row4 = new HBox();
                row4.setAlignment(Pos.CENTER);
                row4.getChildren().addAll(stars);
                grid.add(row1, 0, 9);
                grid.add(row2, 0, 13);
                grid.add(row4, 0, 30);
                grid.add(row3, 0, 34);
                grid.add(row0,0,60);
                grid.setAlignment(Pos.BASELINE_CENTER);
                Scene score = new Scene(grid, width, height);
                score.getStylesheets().add("styles.css");
                Main.stage.setScene(score);
                Main.stage.show();
            }
        };
        button4.setOnAction(scoreevent);
        Font font = Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 60);
        Label c = new Label("C");
        c.setFont(font);
        circle_spawn o1=new circle_spawn(850,130,22,15);
        circle_spawn o2=new circle_spawn(935,130,22,15);
        Label l = new Label("L");
        l.setFont(font);
        Label r = new Label("R");
        r.setFont(font);
        Label Switch = new Label("SWITCH");
        Switch.setPadding(new Insets(0,0,0,30));
        Switch.setFont(font);
        HBox row = new HBox();
        row.setSpacing(10);
        row.setAlignment(Pos.CENTER);
        row.getChildren().addAll(c,o1.root,l,o2.root,r,Switch);
        Button button5 = new Button("EXIT");
        button5.setFont(font1);
        button5.setPrefSize(290, 48);
        button5.setId("button5");
        HBox row2 = new HBox();
        ImageView star3 = new ImageView(star);
        star3.setFitHeight(40);
        star3.setFitWidth(40);
        ImageView star4 = new ImageView(star);
        star4.setFitHeight(40);
        star4.setFitWidth(40);
        row2.setAlignment(Pos.CENTER);
        row2.getChildren().addAll(star3,button3,star4);
        row2.setSpacing(20);
        HBox row3 = new HBox();
        row3.setAlignment(Pos.CENTER);
        ImageView star5 = new ImageView(star);
        star5.setFitHeight(40);
        star5.setFitWidth(40);
        ImageView star6 = new ImageView(star);
        star6.setFitHeight(40);
        star6.setFitWidth(40);
        row3.getChildren().addAll(star5,button4,star6);
        row3.setSpacing(20);
        HBox row4 = new HBox();
        row4.setAlignment(Pos.CENTER);
        ImageView star7 = new ImageView(star);
        star7.setFitHeight(40);
        star7.setFitWidth(40);
        ImageView star8 = new ImageView(star);
        star8.setFitHeight(40);
        star8.setFitWidth(40);
        row4.getChildren().addAll(star7,button5,star8);
        row4.setSpacing(20);
        RotateTransition rt = new RotateTransition(Duration.seconds(2), star1);
        rt.setAutoReverse(true);
        rt.setByAngle(360);
        rt.setCycleCount(RotateTransition.INDEFINITE);
        rt.setInterpolator(Interpolator.LINEAR);
        rt.play();
        RotateTransition rt2 = new RotateTransition(Duration.seconds(2), star2);
        rt2.setAutoReverse(true);
        rt2.setByAngle(360);
        rt2.setCycleCount(RotateTransition.INDEFINITE);
        rt2.setInterpolator(Interpolator.LINEAR);
        rt2.play();
        RotateTransition rt3 = new RotateTransition(Duration.seconds(2), star3);
        rt3.setAutoReverse(true);
        rt3.setByAngle(360);
        rt3.setCycleCount(RotateTransition.INDEFINITE);
        rt3.setInterpolator(Interpolator.LINEAR);
        rt3.play();
        RotateTransition rt4 = new RotateTransition(Duration.seconds(2), star4);
        rt4.setAutoReverse(true);
        rt4.setByAngle(360);
        rt4.setCycleCount(RotateTransition.INDEFINITE);
        rt4.setInterpolator(Interpolator.LINEAR);
        rt4.play();
        RotateTransition rt5 = new RotateTransition(Duration.seconds(2), star5);
        rt5.setAutoReverse(true);
        rt5.setByAngle(360);
        rt5.setCycleCount(RotateTransition.INDEFINITE);
        rt5.setInterpolator(Interpolator.LINEAR);
        rt5.play();
        RotateTransition rt6 = new RotateTransition(Duration.seconds(2), star6);
        rt6.setAutoReverse(true);
        rt6.setByAngle(360);
        rt6.setCycleCount(RotateTransition.INDEFINITE);
        rt6.setInterpolator(Interpolator.LINEAR);
        rt6.play();
        RotateTransition rt7 = new RotateTransition(Duration.seconds(2), star7);
        rt7.setAutoReverse(true);
        rt7.setByAngle(360);
        rt7.setCycleCount(RotateTransition.INDEFINITE);
        rt7.setInterpolator(Interpolator.LINEAR);
        rt7.play();
        RotateTransition rt8 = new RotateTransition(Duration.seconds(2), star8);
        rt8.setAutoReverse(true);
        rt8.setByAngle(360);
        rt8.setCycleCount(RotateTransition.INDEFINITE);
        rt8.setInterpolator(Interpolator.LINEAR);
        rt8.play();
        EventHandler<ActionEvent> exitevent = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent actionEvent)
            {
                Platform.exit();
            }
        };
        button5.setOnAction(exitevent);
        Button button6 = new Button("STORE");
        button6.setPrefSize(410, 68);
        button6.setFont(font1);
        button6.setId("button6");
//        ImageView star9 = new ImageView(star);
//        star9.setFitHeight(40);
//        star9.setFitWidth(40);
//        ImageView star10 = new ImageView(star);
//        star10.setFitHeight(40);
//        star10.setFitWidth(40);
//        RotateTransition rt9 = new RotateTransition(Duration.seconds(2), star9);
//        rt9.setAutoReverse(true);
//        rt9.setByAngle(360);
//        rt9.setCycleCount(RotateTransition.INDEFINITE);
//        rt9.setInterpolator(Interpolator.LINEAR);
//        rt9.play();
//        RotateTransition rt10 = new RotateTransition(Duration.seconds(2), star10);
//        rt10.setAutoReverse(true);
//        rt10.setByAngle(360);
//        rt10.setCycleCount(RotateTransition.INDEFINITE);
//        rt10.setInterpolator(Interpolator.LINEAR);
//        rt10.play();
        HBox row1 = new HBox();
        row1.setAlignment(Pos.CENTER);
        row1.setSpacing(20);
//        row1.getChildren().addAll(star9,button6,star10);
        ImageView soundon = new ImageView(
                new Image("soundon.png")
        );
        ImageView soundoff = new ImageView(
                new Image("soundoff.png")
        );

        Button sound = new Button("");
        sound.setId("sound");
        soundon.setFitHeight(50);
        soundon.setFitWidth(50);
        soundoff.setFitHeight(50);
        soundoff.setFitWidth(50);
        sound.setPrefSize(50, 50);
        sound.setGraphic(soundon);
        EventHandler<ActionEvent> soundchange = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(sound.getGraphic()==soundon)
                {
                    sound.setGraphic(soundoff);
                    Main.Sound_bg=false;
                   Main.mediaPlayer12.stop();

                }

                else
                {
                    sound.setGraphic(soundon);
                    Main.Sound_bg=true;
                    String path1 = "./src/background.wav";
                    Main.media1 = new Media(new File(path1).toURI().toString());
                    Main.mediaPlayer12 = new MediaPlayer(Main.media1);
                   Main.mediaPlayer12.setAutoPlay(true);

                }

            }
        };
        sound.setOnAction(soundchange);
        ImageView musicon = new ImageView(
                new Image("musicon.png")

        );
        ImageView musicoff = new ImageView(
                new Image("musicoff.png")
        );

        Button music = new Button("");
        music.setId("music");
        musicon.setFitHeight(50);
        musicon.setFitWidth(50);
        musicoff.setFitHeight(50);
        musicoff.setFitWidth(50);
        music.setPrefSize(50, 50);
        music.setGraphic(musicon);
        EventHandler<ActionEvent> musicchange = new EventHandler<>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(music.getGraphic()==musicon)
                {
                    music.setGraphic(musicoff);
                    Main.Sound_game=false;
                }

                else
                {
                    music.setGraphic(musicon);
                    Main.Sound_game=true;
                }

            }
        };
        music.setOnAction(musicchange);
        row1.getChildren().addAll(sound,music);
        grid.setAlignment(Pos.BASELINE_CENTER);
        grid.add(row, 0, 5);
        grid.add(play, 0, 14);
        grid.add(row1, 0, 15);
        grid.add(modes, 0, 17);
        grid.add(row2,0,19);
        grid.add(row3,0,21);
        grid.add(row4,0,23);




        EventHandler<ActionEvent> helpevent = new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent e)
            {
                int width = 2080;
                int height = 1000;
                Main.stage.setTitle("help");
                Button back = new Button("");
                back.setId("back");
                back.setText("RETURN");
                back.setFont(font1);
                EventHandler<ActionEvent> returnevent = new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent)
                    {
                        MainMenu obj_display=new MainMenu();
                        Game game=new Game();
                        try {
                            game.game_prev();
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        Game_2 game2=new Game_2();
                        game2.start();
                        Scene scene2 = null;
                        try {
                            scene2 = new Scene(obj_display.main_screen(game.scene,game2.scene), 2080, 1000);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                        }
                        scene2.getStylesheets().add("styles.css");
                        Main.stage.setTitle("Main menu");
                        Main.stage.setScene(scene2);
                    }
                };
                back.setOnAction(returnevent);
                HBox row0 =new HBox();
                row0.setAlignment(Pos.BASELINE_CENTER);
                row0.getChildren().add(back);
                back.setPrefSize(350, 58);
                GridPane grid = new GridPane();
                grid.setHgap(10);
                grid.setVgap(10);
                Text heading = new Text("HELP");
                heading.setFont(Font.font("Montserrat", FontWeight.BOLD, 70));
                heading.setId("helpheading");
                Text colorswitch = new Text("COLOR SWITCH");
                colorswitch.setFont(font1);
                colorswitch.setId("colorswitch");
                Text content = new Text("-> In this game the objective is to collect as many stars as possible and get as far as up as possible without colliding with the obstacles that come in your way.\n-> If the ball is similar color to the obstacle, you can pass unharmed however if the ball is of different color to the obstacle you will lose the game.\n-> Along the way you will collect stars that are your points that can also be used for revival.\n-> You will also encounter many kinds of obstacles along the way and other utilities such as color-changers that changes the ball colors.\n-> You can cotrol your ball using the up key.");
                content.setWrappingWidth(800);
                content.setId("helpcontent");
                HBox row1 = new HBox();
                row1.setAlignment(Pos.CENTER);
                row1.getChildren().add(heading);
                HBox row2 = new HBox();
                row2.setAlignment(Pos.CENTER);
                row2.getChildren().add(colorswitch);
                HBox row3 = new HBox();
                row3.setAlignment(Pos.CENTER);
                row3.getChildren().add(content);
                HBox row4 = new HBox();
                row4.setAlignment(Pos.CENTER);
                Text spaceswitch = new Text("SPACE SWITCH");
                spaceswitch.setFont(font1);
                row4.getChildren().add(spaceswitch);
                spaceswitch.setId("spaceswitch");
                HBox row5 = new HBox();
                Text spaceswitchcontent = new Text("-> In this game the go as far as possible without colliding (dodging by going between the pillars) with the obstacles that come in your way.\n-> You will also encounter many different sizes of obstacles along the way.\n-> You can cotrol your ball using the up key.");
                spaceswitchcontent.setId("spaceswitchcontent");
                spaceswitchcontent.setWrappingWidth(800);
                row5.setAlignment(Pos.CENTER);
                row5.getChildren().add(spaceswitchcontent);
                grid.add(row1, 0, 9);
                grid.add(row2, 0, 14);
                grid.add(row3, 0, 16);
                grid.add(row4, 0, 20);
                grid.add(row5, 0, 22);
                grid.add(row0,0,32);
                grid.setAlignment(Pos.BASELINE_CENTER);
                Scene help = new Scene(grid, width, height);
                help.getStylesheets().add("styles.css");
                Main.stage.setScene(help);
                Main.stage.show();
            }
        };
        button3.setOnAction(helpevent);

        return grid;
    }
}
