package datvexemphim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Movies {
    private String namemovies;
    private String timemovies;
    private boolean movies;
    private static StoredFilesMovies movie = new StoredFilesMovies("movies.json");
    Scanner sc = new Scanner(System.in);
    
    public Movies() {
        this.namemovies = null;
        this.timemovies = null;
        this.movies = false;
    }

    public Movies(String namemovies, String timemovies) {
        this.namemovies = namemovies;
        this.timemovies = timemovies;
    }

    public String getNamemovies() {
        return namemovies;
    }

    public String getTimemovies() {
        return timemovies;
    }

    public boolean checkMovies() {
        return movies;
    }

    public static void searchMovies(String namemovies) {
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
    
    public static List<Object> moviesValid(String namemovies) {
        List<Object> list = new ArrayList<>();
        JsonArray tempMemory = movie.getAll();
        int index = 0;
        index = movie.search("mv", namemovies);

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
    public void moviesName() {
        List<Object> list = new ArrayList<>();
        JsonArray tempMemory = movie.getAll();
        
        // int index = 0;
        // index = movie.search("mv", namemovies);
        for (int i = 0; i < tempMemory.size(); i++) {
            JsonObject jsonObject = tempMemory.get(i).getAsJsonObject();
            System.out.println(jsonObject.get("mv").getAsString());
        }
    }
    

}
