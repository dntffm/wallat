package wallat;

import java.io.FileReader;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Landing {

    @FXML
    private ResourceBundle resources;
    @FXML
    private Pane paneLanding;
    @FXML
    private URL location;
    @FXML
    private ListView listview;
    @FXML
    private Button btnClose;

    @FXML
    private ChoiceBox<String> chKota;

    @FXML
    private Button btnCari;
    @FXML
    Stage stage;
    @FXML
    void initialize() {
        ObservableList<String> listkota = FXCollections.observableArrayList();
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader("C:\\projects\\wallat\\src\\wallat\\kota.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray jsonArray = (JSONArray)jsonObject.get("kota");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()){
                JSONObject city = (JSONObject) iterator.next();
                listkota.add((String) city.get("nama"));
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        chKota.setItems(listkota);
        assert btnClose != null : "fx:id=\"btnClose\" was not injected: check your FXML file 'landing.fxml'.";
        assert chKota != null : "fx:id=\"chKota\" was not injected: check your FXML file 'landing.fxml'.";
        assert btnCari != null : "fx:id=\"btnCari\" was not injected: check your FXML file 'landing.fxml'.";

    }

    public void HandleClick(ActionEvent event){
        if (event.getSource() == btnClose) {
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.close();
        }
        if(event.getSource() == btnCari){
            listview.setItems(FXCollections.observableArrayList("LOADING ....."));
            String cariKota = chKota.getValue();
            String id = cariIdKota(cariKota);
            if (HTTPRequest.dumpData(id).equals("OK")){
                ObservableList<String> listviews = FXCollections.observableArrayList();
                JSONParser parser = new JSONParser();
                try {
                    Object obj = parser.parse(new FileReader("C:\\projects\\wallat\\src\\wallat\\data.json"));
                    JSONObject jsonObject = (JSONObject)obj;
                    JSONObject jadwal = (JSONObject)jsonObject.get("jadwal");
                    JSONObject  data = (JSONObject)jadwal.get("data");
                    listviews.add((String) data.get("tanggal"));
                    listviews.add("Subuh: "+(String) data.get("subuh"));
                    listviews.add("Dhuha: "+(String) data.get("dhuha"));
                    listviews.add("Dzuhur: "+(String) data.get("dzuhur"));
                    listviews.add("Ashar: "+(String) data.get("ashar"));
                    listviews.add("Maghrib: "+(String) data.get("maghrib"));
                    listviews.add("Isya: "+(String) data.get("isya"));

                    listview.setItems(listviews);
                   
                } catch(Exception e) {
                    e.printStackTrace();
                }

            } else{
                listview.setItems(FXCollections.observableArrayList("Tidak ditemukan"));
            }
        }
    }

    public static String cariIdKota(String kota) {
        JSONParser parser = new JSONParser();
        String hasil = "";
        try {
            Object obj = parser.parse(new FileReader("C:\\projects\\wallat\\src\\wallat\\kota.json"));
            JSONObject jsonObject = (JSONObject)obj;
            JSONArray jsonArray = (JSONArray)jsonObject.get("kota");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()){
                JSONObject city = (JSONObject) iterator.next();
                if (kota.equals(city.get("nama"))){
                    hasil = (String) city.get("id");
                }
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        return hasil;
    }

}
