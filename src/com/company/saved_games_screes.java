package com.company;

import com.sun.prism.paint.Color;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class saved_games_screes
{
    TableView tableView;
    TableColumn<Person, Void> column3;
    Label label;
    int i=1;
    saved_games save;
    saved_games_screes()
    {
         tableView= new TableView();
        tableView.setMinHeight(600);
        tableView.setId("save");
        label= new Label("Saved Games");
        label.setFont(Font.font("Arial", FontWeight.BOLD, FontPosture.REGULAR, 60));
        label.setStyle("-fx-text-fill:gold");
        TableColumn<Person, Integer> column0 = new TableColumn<>("S.No");
        column0.setCellValueFactory(new PropertyValueFactory<>("sno"));
        column0.setMinWidth(250);
        TableColumn<Person, String> column1 = new TableColumn<>("ID");
        column1.setMinWidth(500);
        column1.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn<Person, String> column2 = new TableColumn<>("Date");
        column2.setMinWidth(500);
        save=new saved_games();
        column2.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView.getColumns().add(column0);
        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.setPlaceholder(new Label("No saved games"));
        column3 = new TableColumn("Load");
        column3.setMinWidth(500);
        a();

    }
    public void load_list()
    {

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.TOP_CENTER);
        vbox.getChildren().addAll(label,tableView);
        vbox.setId("container");
        vbox.setSpacing(30);
        vbox.setPadding(new Insets(130, 20, 20, 20));
        Scene scene = new Scene(vbox,2080,1000);
        Button back = new Button("");
        back.setId("back");
        back.setText("RETURN");
        Font font1 = Font.font("Montserrat", FontWeight.BOLD, 30);
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
        vbox.getChildren().add(row0);
        scene.getStylesheets().add("styles1.css");
        Main.stage.setScene(scene);
    }
    void add(String name)
    {
        LocalDateTime now=LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        tableView.getItems().add(new Person(i,name,dtf.format(now)));
        i++;
    }
    void a()
    {
        Callback<TableColumn<Person, Void>, TableCell<Person, Void>> cellFactory = new Callback<TableColumn<Person, Void>, TableCell<Person, Void>>() {
            @Override
            public TableCell<Person, Void> call(final TableColumn<Person, Void> param) {
                final TableCell<Person, Void> cell = new TableCell<Person, Void>() {

                    private final Button btn = new Button("START");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Person data = getTableView().getItems().get(getIndex());
                            try {
                                save.deserialize(data.toString());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        column3.setCellFactory(cellFactory);
        tableView.getColumns().add(column3);
    }

}
