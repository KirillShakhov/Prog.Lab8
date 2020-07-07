package lab.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

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
    void exit(MouseEvent event) {

    }

    @FXML
    void leave(MouseEvent event) {

    }

    @FXML
    void makeDragable(MouseEvent event) {

    }

    @FXML
    void initialize() {

    }
}
