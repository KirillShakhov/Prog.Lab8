package lab.Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
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
        assert parent != null : "fx:id=\"parent\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert version_text != null : "fx:id=\"version_text\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert table_button != null : "fx:id=\"table_button\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert theme_button != null : "fx:id=\"theme_button\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert leave_button != null : "fx:id=\"leave_button\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert exit_button != null : "fx:id=\"exit_button\" was not injected: check your FXML file 'mapLight.fxml'.";
        assert language_button != null : "fx:id=\"language_button\" was not injected: check your FXML file 'mapLight.fxml'.";

    }
}
