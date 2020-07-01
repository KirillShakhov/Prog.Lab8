package lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application{
    public static Stage stage;
    public static ClientController clientController;
    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/auth.fxml"));
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


    public static void main(String[] args) {
        ClientController clientController = new ClientController("127.0.0.1", "3030");
        clientController.run();
        launch(args);
    }
}
