package clientfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    public static Stage stage;
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

//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
//        Scene scene = new Scene(new StackPane(l), 640, 480);
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
