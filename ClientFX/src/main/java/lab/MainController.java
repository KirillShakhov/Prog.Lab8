package lab;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab.BasicClasses.MusicBand;
import lab.Commands.ConcreteCommands.RemoveByID;
import lab.Commands.ConcreteCommands.Show;
import lab.Commands.SerializedCommands.Message;

import static lab.ClientController.fastWrite;
import static lab.Main.error_windows;

public class MainController {
    private ObservableList<MusicBand> mbData = FXCollections.observableArrayList();

    private Long id = null;

    @FXML
    private Text name;

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
    private TextField name_field;

    @FXML
    private TextField x_field;

    @FXML
    private TextField y_field;

    @FXML
    private TextField genre_field;

    @FXML
    private TextField album_name_field;

    @FXML
    private TextField album_tracks_field;

    @FXML
    private TextField album_length_field;

    @FXML
    private TextField album_sales_field;

    @FXML
    private TextField np_field;

    @FXML
    private TextField description_field;

    @FXML
    private TextField date_field;

    @FXML
    private Button add_button;

    @FXML
    private Button update_button;

    @FXML
    private Button del_button;

    @FXML
    private TextField createdate_field;

    @FXML
    private TableView<MusicBand> table = new TableView<>();

    @FXML
    private TableColumn<MusicBand, Long> table_ids = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, String> table_name = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, LocalDateTime> table_date = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, String> table_description = new TableColumn<>();

    @FXML
    private TableColumn<?, ?> table_user = new TableColumn<>();

    @FXML
    private Text version_text;

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

        boolean result = isResult(true, name_field, x_field, y_field, genre_field, album_name_field);
        result = isResult(result, album_tracks_field, album_length_field, album_sales_field, np_field, description_field);
        if(!result){
            add_button.setDisable(true);
            del_button.setDisable(true);
            update_button.setDisable(true);
        }
        else{
            if(id == null){
                del_button.setDisable(true);
                update_button.setDisable(true);
            }
            else{
                del_button.setDisable(false);
                update_button.setDisable(false);
            }
            add_button.setDisable(false);
        }
    }

    private boolean isResult(boolean result, TextField album_tracks_field, TextField album_length_field, TextField album_sales_field, TextField np_field, TextField description_field) {
        result = result && !album_tracks_field.getText().equals("");
        result = result && !album_length_field.getText().equals("");
        result = result && !album_sales_field.getText().equals("");
        result = result && !np_field.getText().equals("");
        result = result && !description_field.getText().equals("");
        return result;
    }

    @FXML
    void exit(MouseEvent event) {
        System.exit(0);
    }

    @FXML
    void leave(MouseEvent event) {
        ClientController.name = null;
        ClientController.pass = null;
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/auth.fxml"));
            Main.stage.setScene(new Scene(root));
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update_fields(){
        MusicBand musicBand = table.getSelectionModel().getSelectedItem();
        try {
            id = musicBand.getID();
            name_field.setText(musicBand.getName());
            x_field.setText(String.valueOf(musicBand.getCoordinates().getX()));
            y_field.setText(String.valueOf(musicBand.getCoordinates().getY()));
            genre_field.setText(String.valueOf(musicBand.getGenre()));
            album_name_field.setText(musicBand.getBestAlbum().getName());
            album_tracks_field.setText(String.valueOf(musicBand.getBestAlbum().getTracks()));
            album_length_field.setText(String.valueOf(musicBand.getBestAlbum().getLength()));
            album_sales_field.setText(String.valueOf(musicBand.getBestAlbum().getSales()));
            np_field.setText(String.valueOf(musicBand.getNumberOfParticipants()));
            description_field.setText(musicBand.getDescription());
            date_field.setText(String.valueOf(musicBand.getEstablishmentDate()));
            createdate_field.setText(String.valueOf(musicBand.getCreationDate()));
            del_button.setDisable(false);
            update_button.setDisable(false);
        } catch (Exception ignored){}
    }

    @FXML
    void del(){
        if(id != null){
            String result = fastWrite(new Message(new RemoveByID(), String.valueOf(id)));
            if (result.equals("Объект удален")) {
                mbData = FXCollections.observableArrayList();
                mbData.addAll(ClientController.data);
            } else {
                error_windows(result);
            }
        }
        else{
            error_windows("Вы не выбрали объект");
        }
    }

    @FXML
    void add(){
        if(id != null){
            fastWrite(new Message(new RemoveByID(), String.valueOf(id)));
            mbData = FXCollections.observableArrayList();
            mbData.addAll(ClientController.data);
        }
        else{
            error_windows("Вы не выбрали объект");
        }
    }

    @FXML
    void update(){
        if(id != null){
            fastWrite(new Message(new RemoveByID(), String.valueOf(id)));
            mbData = FXCollections.observableArrayList();
            mbData.addAll(ClientController.data);
        }
        else{
            error_windows("Вы не выбрали объект");
        }
    }

    @FXML
    void show(){
        fastWrite(new Message(new Show()));
        mbData = FXCollections.observableArrayList();
        mbData.addAll(ClientController.data);
    }



    @FXML
    void initialize() {
        version_text.setText(String.valueOf(Main.version));
        name.setText(ClientController.name);

        // устанавливаем тип и значение которое должно хранится в колонке
        table_ids.setCellValueFactory(new PropertyValueFactory<>("ID"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        table_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        table_user.setCellValueFactory(new PropertyValueFactory<>("user_creator"));
        mbData.addAll(ClientController.data);
        table.setItems(mbData);
    }
}
