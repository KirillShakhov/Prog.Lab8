package lab;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import lab.Commands.ConcreteCommands.Auth;
import lab.Commands.ConcreteCommands.Register;
import lab.Commands.SerializedCommands.Message;
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
import static lab.ClientController.readThread;
import static lab.ClientController.writeThread;

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
        String result = "";
        Message message = new Message(new Auth(), login_name_field.getText() + ":::" + login_pass_field.getText());
        try {
            if(writeThread(message)) {
                result = readThread();
            }else {
                result = "сообщение не отправлено";
            }
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }

        if(result.equals("Пользователь не найден")){
            login_name_error.setVisible(true);
            login_pass_error.setVisible(false);
        }
        else{
            login_name_error.setVisible(false);
            if(result.equals("Неправильный пароль")){
                login_pass_error.setVisible(true);
            }
            if (result.equals("Успешная авторизация")){
                ClientController.name = login_name_field.getText();
                ClientController.pass = login_pass_field.getText();
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
            else{
                System.out.println("Проблемы");
                System.out.println(result);
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
        if(!register_pass_field.getText().equals(register_pass2_field.getText())) {
            register_pass_error.setVisible(true);
        }
        else{
            register_pass_error.setVisible(false);
            String result = "";
            Message message = new Message(new Register(), register_name_field.getText() + ":::" + register_pass_field.getText());
            try {
                if(writeThread(message)) {
                    result = readThread();
                }else {
                    result = "сообщение не отправлено";
                }
            } catch (IOException | ClassNotFoundException | InterruptedException e) {
                e.printStackTrace();
            }
            if(result.equals("Имя пользователя занято")){
                register_name_error.setVisible(true);
                register_pass_error.setVisible(false);
            }
            else if(result.equals("Пользователь зарегистрирован")){
                register_name_error.setVisible(false);
                register_pass_error.setVisible(false);
                ClientController.name = register_name_field.getText();
                ClientController.pass = register_pass_field.getText();
                try {
                    Parent root = FXMLLoader.load(getClass().getResource("/main.fxml"));
                    Main.stage.setScene(new Scene(root));
                    Main.stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                System.out.println("проблемы");
                System.out.println(result);
            }
        }
    }

    @FXML
    void initialize() {

    }
}
