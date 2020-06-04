package wallat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Iterator;

public class Main extends Application {
    private double x,y;
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("home.fxml"));
        primaryStage.setScene(new Scene(root));
        //set stage borderless
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(mouseEvent -> {
            x = mouseEvent.getSceneX();
            y = mouseEvent.getSceneY();
        });

        root.setOnMouseDragged(mouseEvent -> {

            primaryStage.setX(mouseEvent.getScreenX() - x);
            primaryStage.setY(mouseEvent.getScreenY() - y);

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);

    }
}
