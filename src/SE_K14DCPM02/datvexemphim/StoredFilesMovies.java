package datvexemphim;

import java.io.FileReader;
import java.io.FileWriter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class StoredFilesMovies {
    private String storedFileMovies;
    private JsonArray memory;// data on RAM

    /**
     * @param storedFileMovies
     */
    public StoredFilesMovies(String storedFileMovies) {
        this.storedFileMovies = storedFileMovies;
        this.memory = read();
    }

    /**
     * 
     * @param key
     * @param value
     * @return: index of elements *** -1: not found
     */
    public int search(String key, String value) {
        int index = -1;
        String namemovies = null;
        for (int i = 0; i < memory.size(); i++) {
            JsonObject jsonObject = memory.get(i).getAsJsonObject();
            
            namemovies = jsonObject.get(key).getAsString();
            if (value.equalsIgnoreCase(namemovies)) {
                index = i;
                break;
            }
        }
        return index;
    }

    public JsonArray read() {
        JsonArray jsonArray = null;
        
        try (FileReader reader = new FileReader(storedFileMovies)) {
            jsonArray = (JsonArray) JsonParser.parseReader(reader);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonArray;
    }

    // add to memory
    public void update(String namemovies, String timemovies) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mv", namemovies);
        jsonObject.addProperty("tmv", timemovies);

        memory.add(jsonObject);
    }

    public void write() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(storedFileMovies)) {
            gson.toJson(memory, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public JsonArray getAll(){
        return this.memory;
    }

}
