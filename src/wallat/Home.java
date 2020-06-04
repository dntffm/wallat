package wallat;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;

public class Home {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCls;

    @FXML
    private Button btnJadwal;

    @FXML
    private Button btnKeluar;

    public void HandleClick(ActionEvent event){
        if (event.getSource() == btnJadwal){
            Helper.changePage(event,"landing");
        }
        if (event.getSource() == btnCls) {
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    @FXML
    void initialize() {
        //HTTPRequest.dumpData();
        assert btnCls != null : "fx:id=\"btnCls\" was not injected: check your FXML file 'home.fxml'.";
        assert btnJadwal != null : "fx:id=\"btnJadwal\" was not injected: check your FXML file 'home.fxml'.";
        assert btnKeluar != null : "fx:id=\"btnKeluar\" was not injected: check your FXML file 'home.fxml'.";

    }
}
