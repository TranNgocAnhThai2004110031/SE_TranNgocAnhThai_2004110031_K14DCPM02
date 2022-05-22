package datvexemphim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UITerminalMovies {
    private Movies mvObject = new Movies();
    // private static StoredFilesMovies storedFilesMovies = new StoredFilesMovies("movies.json");
    public static Scanner scanner = new Scanner(System.in);

    public UITerminalMovies() {
    }

    public UITerminalMovies(Movies mvObject) {
        this.mvObject = mvObject;
    }

    public List<Object> moviesInput() {
        List<Object> list = new ArrayList<>();
        System.out.print("NAME MOVIES: ");
        String name = scanner.nextLine();
        list.add(name);
        return list;
    }

    public void chooseMovies(String namemovies) {
        List<Object> listCheck;
        
        listCheck = moviesValid(namemovies);

        if (!(boolean) listCheck.get(0)) {
            System.out.println(listCheck.get(1));
        } else {          
            System.out.println(listCheck.get(1));
            System.out.println(listCheck.get(2));
            System.out.println(listCheck.get(3));
        }
    }
    
    private List<Object> moviesValid(String namemovies) {
        List<Object> list = new ArrayList<>();
        JsonArray tempMemory = Movies.movie.getAll();
        int index = -1;
        index = Movies.movie.search("mv", namemovies);

        if (index != -1) {
            list.add(true);// 0
            list.add("[MOVIES EXIST] Movies name was found.");// 1
            JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
            String mv = jsonObject.get("mv").getAsString();
            String tmv = jsonObject.get("tmv").getAsString();
            list.add(mv);//2
            list.add(tmv); //3
            return list;
        }else {
            list.add(false);
            list.add("[NO MOVIES] Movies name doesn't exist.");            
        }
        return list;
    }
}
