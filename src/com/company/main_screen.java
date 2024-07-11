//package com.company;
//
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.layout.*;
//import javafx.stage.Stage;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.scene.text.*;
//import javafx.scene.image.*;
//import java.io.*;
//import javafx.geometry.*;
//
//class main_screen
//{
//    @Override
//    public void start(Stage stage) {
////        int width = 400;
////        int height = 600;
////        GridPane grid = new GridPane();
////        grid.setHgap(10);
////        grid.setVgap(10);
////        HBox modes = new HBox();
////        modes.setSpacing(5);
////        Button button1 = new Button("SAVED GAMES");
////        button1.setId("modes");
////        button1.setPrefSize(230,45);
////        Font font1 = Font.font("Montserrat",FontWeight.BOLD, 20);
////        button1.setFont(font1);
////        Image star = new Image("star.jpg");
////        ImageView star1 = new ImageView(star);
////        star1.setFitHeight(20);
////        star1.setFitWidth(20);
////        ImageView star2 = new ImageView(star);
////        star2.setFitHeight(20);
////        star2.setFitWidth(20);
////        modes.getChildren().addAll(star1,button1,star2);
////        modes.setAlignment(Pos.CENTER);
////        ImageView playimage = new ImageView(
////                new Image("playbutton.png")
////        );
////        playimage.setFitHeight(100);
////        playimage.setFitWidth(100);
////        Button button2 = new Button("",playimage);
////        button2.setId("play");
////        button2.setPrefSize(130,130);
////        HBox play = new HBox();
////        play.setAlignment(Pos.CENTER);
////        play.getChildren().add(button2);
////        ImageView help = new ImageView(
////                new Image("help.png")
////        );
////        ImageView score = new ImageView(
////                new Image("score.png")
////        );
////        score.setFitHeight(40);
////        score.setFitWidth(40);
////        help.setFitHeight(40);
////        help.setFitWidth(40);
////        Button button3 = new Button("",help);
////        EventHandler<ActionEvent> helpevent = new EventHandler<ActionEvent>(){
////            public void handle(ActionEvent e)
////            {
////                int width = 400;
////                int height = 600;
////                stage.setTitle("help");
////                GridPane grid = new GridPane();
////                grid.setHgap(10);
////                grid.setVgap(10);
////                Text heading = new Text("HELP");
////                heading.setId("helpheading");
////                Text content = new Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.");
////                content.setWrappingWidth(390);
////                content.setId("helpcontent");
////                HBox row1 = new HBox();
////                row1.setAlignment(Pos.CENTER);
////                row1.getChildren().add(heading);
////                HBox row2 = new HBox();
////                row2.setAlignment(Pos.CENTER);
////                row2.getChildren().add(content);
////                grid.add(row1,0,9);
////                grid.add(row2,0,11);
////                grid.setAlignment(Pos.BASELINE_CENTER);
////                Scene help = new Scene(grid,width,height);
////                help.getStylesheets().add("styles.css");
////                stage.setScene(help);
////                stage.show();
////            }
////        };
////        button3.setOnAction(helpevent);
////        button3.setId("button3");
////        Button button4 = new Button("",score);
////        button4.setId("button4");
////        EventHandler<ActionEvent> scoreevent = new EventHandler<ActionEvent>(){
////            public void handle(ActionEvent e)
////            {
////                int width = 400;
////                int height = 600;
////                stage.setTitle("help");
////                GridPane grid = new GridPane();
////                grid.setHgap(10);
////                grid.setVgap(10);
////                Text heading = new Text("HIGHEST SCORE");
////                heading.setId("scoreheading");
////                Text stars = new Text("STARS");
////                stars.setId("scorestars");;
////                HBox row1 = new HBox();
////                row1.setAlignment(Pos.CENTER);
////                row1.getChildren().add(heading);
////                HBox row2 = new HBox();
////                row2.setAlignment(Pos.CENTER);
////                row2.getChildren().add(stars);
////                grid.add(row1,0,9);
////                grid.add(row2,0,30);
////                grid.setAlignment(Pos.BASELINE_CENTER);
////                Scene score = new Scene(grid,width,height);
////                score.getStylesheets().add("styles.css");
////                stage.setScene(score);
////                stage.show();
////            }
////        };
////        button4.setOnAction(scoreevent);
////        Text colorswitch = new Text("COLOR SWITCH");
////        colorswitch.setId("heading");
////        HBox row = new HBox();
////        row.setSpacing(20);
////        row.setAlignment(Pos.CENTER);
////        row.getChildren().addAll(button3,colorswitch,button4);
////        grid.setAlignment(Pos.BASELINE_CENTER);
////        grid.add(row,0,9);
////        grid.add(play,0,14);
////        grid.add(modes,0,23);
////        Scene scene = new Scene(grid,width,height);
////        scene.getStylesheets().add("styles.css");
////        stage.setTitle("Main menu");
////        stage.setScene(scene);
////        stage.show();
//    }
//    public static void main(String args[]){
//        launch(args);
//    }
//}