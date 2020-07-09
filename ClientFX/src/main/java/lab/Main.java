package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lab.Controllers.ErrorController;

import java.io.IOException;


public class Main extends Application{
    public static Stage stage;
    public static Stage stage_error = null;
    public static ClientController clientController;


    public static final double version = 0.4;
    public static String language = "en_US";
    public static String theme = "Light";
    public static final String host = "127.0.0.1";
    public static final String port = "3030";



    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/auth"+Main.theme+".fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(new Scene(root));
//        primaryStage.setMaxWidth(820);
//        primaryStage.setMaxHeight(600);
        primaryStage.setWidth(800);
        primaryStage.setHeight(533);
//        primaryStage.setMinWidth(820);
//        primaryStage.setMinHeight(600);
        primaryStage.setResizable(false);

        primaryStage.alwaysOnTopProperty();
        primaryStage.alwaysOnTopProperty();
        primaryStage.show();

    }

    public static void error_windows(String text, Color color) {
        try {
            ErrorController.text = text;
            ErrorController.color = color;
            Parent root = FXMLLoader.load(Main.class.getResource("/error"+Main.theme+".fxml"));
            if(stage_error == null){
                stage_error = new Stage();
                stage_error.initStyle(StageStyle.UNDECORATED);
                stage_error.setScene(new Scene(root));
                stage_error.showAndWait();
            }
            else{
                stage_error.hide();
                stage_error.setScene(new Scene(root));
                stage_error.showAndWait();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        ClientController clientController = new ClientController(host, port);
        clientController.run();
        launch(args);
    }

    public static void change_language(){
        switch (Main.language) {
            case "en_US":
                Main.language = "ru_RU";
                break;
            case "ru_RU":
                Main.language = "mk";
                break;
            case "mk":
                Main.language = "fr";
                break;
            case "fr":
                Main.language = "sp_HD";
                break;
            case "sp_HD":
                Main.language = "en_US";
                break;
        }
    }


    public static void change_theme() {
        switch (Main.theme) {
            case "Dark":
                Main.theme = "Light";
                break;
            case "Light":
                Main.theme = "Dark";
                break;
        }
    }
}
