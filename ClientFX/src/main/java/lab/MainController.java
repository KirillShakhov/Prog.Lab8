package lab;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

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
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lab.BasicClasses.Album;
import lab.BasicClasses.Coordinates;
import lab.BasicClasses.MusicBand;
import lab.BasicClasses.MusicGenre;
import lab.Commands.ConcreteCommands.Add;
import lab.Commands.ConcreteCommands.RemoveByID;
import lab.Commands.ConcreteCommands.Show;
import lab.Commands.SerializedCommands.Message;
import lab.Commands.Utils.Creaters.ElementCreator;
import lab.Commands.Utils.Readers.EnumReaders.GenreReader;

import static lab.ClientController.fastWrite;
import static lab.Main.error_windows;

public class MainController {
    private ObservableList<MusicBand> mbData = FXCollections.observableArrayList();

    private Long id = null;


    @FXML
    private AnchorPane parent;

    @FXML
    private Button add_button;

    @FXML
    private Button update_button;

    @FXML
    private Button del_button;

    @FXML
    private Circle circle;

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
    private Text chech_error;

    @FXML
    private Text text_error;

    @FXML
    private Polygon triangle;

    @FXML
    private Rectangle square;

    @FXML
    private TextField createdate_field;

    @FXML
    private Button more_button;

    @FXML
    private Button del_no_button;

    @FXML
    private Button del_yes_button;

    @FXML
    private Button filter_button;

    @FXML
    private Text name;

    @FXML
    private Text version_text;

    @FXML
    private Button map_button;

    @FXML
    private Button theme_button;

    @FXML
    private Button leave_button;

    @FXML
    private Button exit_button;

    @FXML
    private Button language_button;

    @FXML
    private Button clean_fields_button;

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
        update_table();
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
            Parent root = FXMLLoader.load(getClass().getResource("/authLight.fxml"));
            Main.stage.setScene(new Scene(root));
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void update_fields(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
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
            date_field.setText(format.format(musicBand.getEstablishmentDate()));
            createdate_field.setText(String.valueOf(musicBand.getCreationDate()));
            del_yes_button.setVisible(false);
            del_no_button.setVisible(false);
            del_button.setVisible(true);
            add_button.setDisable(false);
            del_button.setDisable(false);
            update_button.setDisable(false);
        } catch (Exception ignored){}
    }

    @FXML
    void del(){
        if(id != null){
            del_button.setVisible(false);
            del_yes_button.setVisible(true);
            del_no_button.setVisible(true);
        }
        else{
            error_windows("Вы не выбрали объект");
        }
    }
    @FXML
    void delYes(){
        if(id != null){
            String result = fastWrite(new Message(new RemoveByID(), String.valueOf(id)));
            if (result.equals("Объект удален")) {
                update_table();
            } else {
                error_windows(result);
            }
            del_button.setVisible(true);
            del_yes_button.setVisible(false);
            del_no_button.setVisible(false);
        }
        else{
            error_windows("Вы не выбрали объект");
        }
    }
    @FXML
    void delNo(){
        del_button.setVisible(true);
        del_yes_button.setVisible(false);
        del_no_button.setVisible(false);
        id = null;
    }

    void update_table(){
        mbData = FXCollections.observableArrayList();
        mbData.addAll(ClientController.data);
        table.setItems(mbData);
    }

    @FXML
    void add(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            boolean res = true;
            if (name_field.getText().isEmpty()) {
                res = false;
                viewError("Имя пустое");
                name_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (x_field.getText().isEmpty() || Double.parseDouble(x_field.getText()) <= -687) {
                res = false;
                viewError("X число больше -687");
                x_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (y_field.getText().isEmpty() || Float.parseFloat(y_field.getText()) < -1000f) {
                res = false;
                viewError("Y число больше -1000");
                y_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (np_field.getText().isEmpty() || Integer.parseInt(np_field.getText()) <= 0) {
                res = false;
                viewError("numberOfParticipants больше 0");
                np_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (description_field.getText().isEmpty()) {
                res = false;
                viewError("Описание пустое");
                description_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (genre_field.getText().isEmpty() || !GenreReader.checkExist(genre_field.getText())) {
                res = false;
                viewError("genre(PSYCHEDELIC_ROCK,RAP,POP,POST_ROCK,POST_PUNK)");
                genre_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (album_name_field.getText().isEmpty()) {
                res = false;
                viewError("Имя альбома пустое");
                album_name_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (album_tracks_field.getText().isEmpty() || Integer.parseInt(album_tracks_field.getText()) <= 0) {
                res = false;
                viewError("Album tracks число больше 0");
                album_tracks_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (album_length_field.getText().isEmpty() || Integer.parseInt(album_length_field.getText()) <= 0) {
                res = false;
                viewError("Album length число больше 0");
                album_length_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if (album_sales_field.getText().isEmpty() || Integer.parseInt(album_sales_field.getText()) <= 0) {
                res = false;
                viewError("Album sales число больше 0");
                album_sales_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
            }
            if(res) {
                viewError("");
                setDefaultTheme();
                MusicBand musicBand = new MusicBand(0L, name_field.getText(), new Coordinates(Double.parseDouble(x_field.getText()), Float.parseFloat(y_field.getText())), LocalDateTime.now(), Integer.parseInt(np_field.getText()), description_field.getText(), format.parse(date_field.getText()), Enum.valueOf(MusicGenre.class, genre_field.getText()), new Album(album_name_field.getText(), Integer.parseInt(album_tracks_field.getText()), Integer.parseInt(album_length_field.getText()), Integer.parseInt(album_sales_field.getText())), ClientController.name);
                fastWrite(new Message(new Add(), musicBand));
                mbData = FXCollections.observableArrayList();
                mbData.addAll(ClientController.data);
            }
        } catch (ParseException e) {
            viewError("Неправильный формат у даты");
            date_field.setStyle("-fx-background-color: red; -fx-background-radius: 10");
        } catch (Exception e){
            viewError("Что-то пошло не так");
            e.printStackTrace();
        }
    }

    @FXML
    void clean_fields(){
        id = null;
        name_field.setText("");
        x_field.setText("");
        y_field.setText("");
        np_field.setText("");
        description_field.setText("");
        genre_field.setText("");
        album_name_field.setText("");
        album_tracks_field.setText("");
        album_length_field.setText("");
        album_sales_field.setText("");
        date_field.setText("");
        name_field.setText("");
        createdate_field.setText("");
        setDefaultTheme();
    }

    private void setDefaultTheme() {
        name_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        x_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        y_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        np_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        description_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        genre_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        album_name_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        album_tracks_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        album_length_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        album_sales_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
        date_field.setStyle("-fx-background-color:  #ddfff8; -fx-background-radius: 10");
    }

    void viewError(String text){
        if(text.equals("")){
            chech_error.setVisible(false);
            text_error.setText(text);
            text_error.setVisible(false);
        }
        else {
            chech_error.setVisible(true);
            text_error.setText(text);
            text_error.setVisible(true);
        }
    }


    @FXML
    void update(){
        if(id != null){
            fastWrite(new Message(new RemoveByID(), String.valueOf(id)));
            update_table();
        }
        else{
            error_windows("Вы не выбрали объект");
        }
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
