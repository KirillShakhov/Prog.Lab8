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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import lab.BasicClasses.MusicBand;

public class MainController {
    private ObservableList<MusicBand> mbData = FXCollections.observableArrayList();



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

    void updateTable(){
        /*
        for(MusicBand musicBand : ClientController.data){
            (musicBand.getID(), musicBand.getName(), musicBand.getCreationDate(), musicBand.getDescription());
        }

         */
        //noinspection unchecked
        table.getColumns().removeAll();

        //noinspection unchecked
        table.getColumns().addAll(table_ids, table_name, table_date, table_description);
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
