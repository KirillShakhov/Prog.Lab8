package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class Main extends Application{
    public static Stage stage;
    public static Stage stage_error = null;
    public static ClientController clientController;


    public static final double version = 0.1;
    public static final String host = "127.0.0.1";
    public static final String port = "3030";


    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/authLight.fxml"));
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

    public static void error_windows(String text) {
        try {
            ErrorController.text = text;
            Parent root = FXMLLoader.load(Main.class.getResource("/error.fxml"));
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
}
