package datvexemphim;

import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StoredFileSeat {
    private String storedFileSeat;
    private JsonArray memorySeat;

    public StoredFileSeat(String storedFileSeat){
        this.storedFileSeat = storedFileSeat;
        this.memorySeat = read();
    }

    public JsonArray read() {
        JsonArray jsonArray = null;
        
        try (FileReader reader = new FileReader(storedFileSeat)) {
            jsonArray = (JsonArray) JsonParser.parseReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    public void write() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(storedFileSeat)) {
            gson.toJson(memorySeat, writer);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public int searchRow(String key, String value){
        int index = -1;
        String row = null;
        for(int i = 0; i < memorySeat.size(); i++){
            JsonObject jsonObject = memorySeat.get(i).getAsJsonObject();
            row = jsonObject.get(key).getAsString();
            if(value.equals(row)){
                index = i;
                break;
            }
        }
        return index;
    }

    public int searchColumn(String key, Integer value){
        int index = -1;
        Integer column = null;
        for(int i = 0; i < memorySeat.size(); i++){
            JsonObject jsonObject = memorySeat.get(i).getAsJsonObject();
            column = jsonObject.get(key).getAsInt();
            if(value == column){
                index = i;
                break;
            }
        }
        return index;
    }

    public void update(String row, Integer column) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("row", row);
        jsonObject.addProperty("column", column);
        memorySeat.add(jsonObject);
    }

    public JsonArray getAll(){
        return this.memorySeat;
    }

}
