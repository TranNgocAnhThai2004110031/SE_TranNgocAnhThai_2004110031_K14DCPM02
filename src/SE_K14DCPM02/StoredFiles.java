import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
// import java.io.Reader;
// import java.lang.reflect.Type;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class StoredFiles {
    private String stored_file;

     List<Account> memory = new ArrayList<>();

    public StoredFiles() {
    }

    public StoredFiles(String stored_file) {
        this.stored_file = stored_file;
    }

    public void read(){
        File file = new File("data.json");
        
        try {
            InputStream inputStream = new FileInputStream(file);
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
    
            String line = "";

            while((line = reader.readLine()) != null){
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Gson gson = new Gson();
        // try(Reader reader = Files.newBufferedReader(Paths.get("data.json"))){
        //     gson.fromJson(reader, (Type) memory);
        // }catch (Exception e) {
        //     e.printStackTrace();
        // }
        
    }    

    public void write() {
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter("data.json")){
            gson.toJson(memory, fileWriter);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        
    }

    public void get_all() {
        
    }    
     
    public void search() {
        
    }
}
