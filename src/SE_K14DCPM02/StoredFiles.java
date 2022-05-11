import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
            Gson gson = new Gson();
        
            Reader reader = Files.newBufferedReader(Paths.get("data.json"));
        
            memory = Arrays.asList(gson.fromJson(reader, Account[].class));
        
            for (Account account : memory) {
                System.out.println(account);
            }
        
            reader.close();
        
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }    

    public void write() {
        GsonBuilder gsonBuilder = new GsonBuilder(); //dạng file json in ra đẹp hơn
        Gson gson = gsonBuilder.create();
        // Gson gson = new Gson(); // dạng của thầy
        try(Writer writer = Files.newBufferedWriter(Paths.get("data.json"))){
            gsonBuilder.setPrettyPrinting().create().toJson(list, writer);
            // gson.toJson(list, writer);
            writer.close();
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        for(Account a : memory){
            list.add(a);
        }
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
