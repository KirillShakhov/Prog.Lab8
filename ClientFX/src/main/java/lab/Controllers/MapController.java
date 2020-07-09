package lab.Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import lab.BasicClasses.MusicBand;
import lab.ClientController;
import lab.Main;

public class MapController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private Canvas canvas;

    @FXML
    private Text name;

    @FXML
    private Text version_text;

    @FXML
    private Button table_button;

    @FXML
    private Button theme_button;

    @FXML
    private Button leave_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button language_button;

    @FXML
    private Line line_x;

    @FXML
    private Line line_y;

    @FXML
    private AnchorPane objects;

    @FXML
    private Circle circle;

    @FXML
    private Polygon triangle;

    @FXML
    private Rectangle square;

    @FXML
    private Circle user_color;

    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void leave(MouseEvent event) {
        ClientController.name = null;
        ClientController.pass = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/auth"+ Main.theme+".fxml"));
            Main.stage.setScene(new Scene(root));
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double xOffSet;
    private double yOffSet;
    @FXML
    void makeDragable(MouseEvent event) {
        parent.setOnMousePressed(events -> {
            xOffSet = events.getSceneX();
            yOffSet = events.getSceneY();
        });
        parent.setOnMouseDragged(events -> {
            Main.stage.setX(events.getScreenX() - xOffSet);
            Main.stage.setY(events.getScreenY() - yOffSet);
        });
    }


    @FXML
    void initialize() {
        version_text.setText(String.valueOf(Main.version));
        name.setText(ClientController.name);
        update_language();
        animation();
    }

    @FXML
    private void animation() {
        double const_x = ((line_x.getEndX()-line_x.getStartX())/2)+30;
        double const_y = ((line_y.getEndY() - line_y.getStartY())/2)+24.5;


        for (MusicBand musicBand : ClientController.data) {
            int r = Math.abs(musicBand.getName().hashCode() % 250);
            int g = Math.abs(musicBand.getName().hashCode() * 250000 % 250);
            int b = Math.abs(musicBand.getName().hashCode() * 250000000 % 250);
            Color color = Color.rgb(r, g, b);
            r = Math.abs(musicBand.getUser_creator().hashCode() % 250);
            g = Math.abs(musicBand.getUser_creator().hashCode() * 250000 % 250);
            b = Math.abs(musicBand.getUser_creator().hashCode() * 250000000 % 250);
            Color ucolor = Color.rgb(r, g, b);


            Circle t = new Circle(musicBand.getNumberOfParticipants());
            t.setCenterX(const_x);
            t.setCenterY(const_y);
            t.setFill(ucolor);

            KeyValue xValue = new KeyValue(t.centerXProperty(), const_x+musicBand.getCoordinates().getX());
            KeyValue yValue = new KeyValue(t.centerYProperty(), const_y-musicBand.getCoordinates().getY());

            KeyFrame keyFrame = new KeyFrame(Duration.millis(1000), xValue, yValue);


            Timeline timeline = new Timeline();
            timeline.getKeyFrames().addAll(keyFrame);
            timeline.play();


            objects.getChildren().add(t);
        }
    }

    private void update_language() {
        String[] lan = Main.language.split("_");
        Locale current;
        if(lan.length>1) {
            current = new Locale(lan[0], lan[1]);
        }else{
            current = new Locale(lan[0]);
        }
        ResourceBundle rb = ResourceBundle.getBundle("Client", current);
        table_button.setText(rb.getString("table_button"));
        language_button.setText(rb.getString("language_button"));
    }

    @FXML
    public void table(MouseEvent mouseEvent) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/main"+Main.theme+".fxml"));
            Main.stage.setScene(new Scene(root));
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void change_language(MouseEvent mouseEvent) {
        Main.change_language();
        update_language();
    }

    public void change_theme(MouseEvent mouseEvent) {
        Main.change_theme();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/map"+Main.theme+".fxml"));
            Main.stage.setScene(new Scene(root));
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
