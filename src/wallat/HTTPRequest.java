package wallat;

import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;

public class HTTPRequest {
    public static String dumpData(String id){
        String host = "api.banghasan.com";
        int port = 80;
        String result = "";
        LocalDate date = LocalDate.now();
        try {
            Socket socket = new Socket(host,port);
            PrintWriter wtr = new PrintWriter(socket.getOutputStream());

            wtr.println("GET /sholat/format/json/jadwal/kota/"+id+"/tanggal/"+date+" HTTP/1.1");
            wtr.println("host: api.banghasan.com");
            wtr.println("");
            wtr.flush();

            BufferedReader bufferedReader= new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String sb="";
            String response;

            while ((response = bufferedReader.readLine()) !=null ){
                if (response.equals("Connection: keep-alive")) break;

            }
            while ((response = bufferedReader.readLine()) !=null ){
                sb+=response+"\n";
            }
            String path = "C:\\projects\\wallat\\src\\wallat\\data.json";

            Files.writeString(Paths.get(path),sb);

            bufferedReader.close();
            wtr.close();
            result = "OK";
        } catch (Exception e){
            System.out.println(e);
            result = "notOK";
        }

        return result;

    }

}
