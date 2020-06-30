package clientfx;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private Button register_button1;

    @FXML
    private Button register_button11;

    @FXML
    private Button register_button12;

    @FXML
    private Button exit_button111;

    @FXML
    private Button exit_button11;

    @FXML
    private Button leave_button;

    @FXML
    private Button exit_button;

    @FXML
    private TableView<?> table;

    @FXML
    private TableColumn<?, ?> table_id;

    @FXML
    private TableColumn<?, ?> table_name;

    @FXML
    private TableColumn<?, ?> table_date;

    @FXML
    private TableColumn<?, ?> table_description;


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
    void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert parent != null : "fx:id=\"parent\" was not injected: check your FXML file 'main.fxml'.";
        assert register_button1 != null : "fx:id=\"register_button1\" was not injected: check your FXML file 'main.fxml'.";
        assert register_button11 != null : "fx:id=\"register_button11\" was not injected: check your FXML file 'main.fxml'.";
        assert register_button12 != null : "fx:id=\"register_button12\" was not injected: check your FXML file 'main.fxml'.";
        assert exit_button111 != null : "fx:id=\"exit_button111\" was not injected: check your FXML file 'main.fxml'.";
        assert exit_button11 != null : "fx:id=\"exit_button11\" was not injected: check your FXML file 'main.fxml'.";
        assert leave_button != null : "fx:id=\"exit_button2\" was not injected: check your FXML file 'main.fxml'.";
        assert exit_button != null : "fx:id=\"exit_button\" was not injected: check your FXML file 'main.fxml'.";

    }
}
