package clientfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import static java.lang.Thread.sleep;

public class AuthController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane parent;

    @FXML
    private TextField register_name_field;

    @FXML
    private TextField register_pass2_field;

    @FXML
    private Button register_button;

    @FXML
    private TextField register_pass_field;

    @FXML
    private Text register_pass_error;

    @FXML
    private Text register_name_error;

    @FXML
    private TextField login_name_field;

    @FXML
    private Button login_button;

    @FXML
    private TextField login_pass_field;

    @FXML
    private Text login_pass_error;

    @FXML
    private Text login_name_error;

    @FXML
    private Button exit_button;

    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void login(MouseEvent event) {
        if(!login_name_field.getText().equals("123")){
            login_name_error.setVisible(true);
            login_pass_error.setVisible(false);
        }
        else{
            login_name_error.setVisible(false);
            if(!login_pass_field.getText().equals("123")){
                login_pass_error.setVisible(true);
            }
            if (login_name_field.getText().equals("123") && login_pass_field.getText().equals("123")){
                login_pass_error.setVisible(false);
                //login_button.getScene().getWindow().hide();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                    Main.stage.setScene(new Scene(root));
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
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
    void register(MouseEvent event) {
        if(register_name_field.getText().equals("123")){
            register_name_error.setVisible(true);
            register_pass_error.setVisible(false);
        }
        else{
            register_name_error.setVisible(false);
            //noinspection RedundantIfStatement
            if(register_pass_field.getText().equals(register_pass2_field.getText())) {
                register_pass_error.setVisible(false);
                //TODO регистрация
            }
            else{
                register_pass_error.setVisible(true);
            }
        }
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

    }
}
