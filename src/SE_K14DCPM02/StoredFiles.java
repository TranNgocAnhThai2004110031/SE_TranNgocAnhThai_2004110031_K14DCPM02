import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
// import java.io.Reader;
// import java.lang.reflect.Type;
// import java.nio.file.Files;
// import java.nio.file.Path;
// import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.google.gson.Gson;

public class StoredFiles {
    private String stored_file;

    List<Account> memory = new ArrayList<>();
    List<Account> list = new ArrayList<>(memory);

    public StoredFiles() {
    }

    public StoredFiles(String stored_file) {
        this.stored_file = stored_file;
    }

    public void read(){
        // File file = new File("data.json");
        
        // try {
        //     InputStream inputStream = new FileInputStream(file);
        //     InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        //     BufferedReader reader = new BufferedReader(inputStreamReader);
    
        //     String line = "";

        //     while((line = reader.readLine()) != null){
        //         System.out.println(line);
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        // Gson gson = new Gson();
        // try(Reader reader = Files.newBufferedReader(Paths.get("data.json"))){
        //     gson.fromJson(reader, (Type) memory);
        // }catch (Exception e) {
        //     e.printStackTrace();
        // }
        
        
        try {
            // create Gson instance
            Gson gson = new Gson();
        
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("data.json"));

            // update();
        
            // convert JSON array to list of books
            memory = Arrays.asList(gson.fromJson(reader, Account[].class));
        
            // print books
            for (Account account : memory) {
                System.out.println(account);
            }
        
            // close reader
            reader.close();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }    

    public void write() {
        Gson gson = new Gson();
        try(FileWriter fileWriter = new FileWriter("data.json")){
            gson.toJson(list, fileWriter);
            fileWriter.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Account account) {
        list = memory;
        list.add(account);
    }

    public void get_all() {
        
    }    
     
    public void search() {
        Account account1 = null;
        System.out.print("Nhập username hoặc email account bạn cần tìm: ");
        String searched = UITerminal.sc.nextLine();
        System.out.println("Account tìm được: ");
        for(Account account:memory){
            if(account1.getUsername().equalsIgnoreCase(searched) || account1.getEmail().equals(searched)){
                account1 = account;
                System.out.println(account1);
            }
        }
    }
}
