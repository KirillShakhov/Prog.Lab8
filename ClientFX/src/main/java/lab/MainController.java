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
import lab.BasicClasses.MusicBand;

public class MainController {
    private ObservableList<MusicBand> mbData = FXCollections.observableArrayList();

    private Long id;

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
    private TableView<MusicBand> table = new TableView<>();

    @FXML
    private TableColumn<MusicBand, Long> table_ids = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, String> table_name = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, LocalDateTime> table_date = new TableColumn<>();

    @FXML
    private TableColumn<MusicBand, String> table_description = new TableColumn<>();


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
        date_field.setText(String.valueOf(musicBand.getCreationDate()));
        del_button.setDisable(false);
        update_button.setDisable(false);
    }



    @FXML
    void initialize() {

        name.setText(ClientController.name);

        // устанавливаем тип и значение которое должно хранится в колонке
        table_ids.setCellValueFactory(new PropertyValueFactory<>("ID"));
        table_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        table_date.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        table_description.setCellValueFactory(new PropertyValueFactory<>("description"));
        mbData.addAll(ClientController.data);
        table.setItems(mbData);
        for (MusicBand musicBand : ClientController.data){
            System.out.println(musicBand.getID());
        }
    }
}
