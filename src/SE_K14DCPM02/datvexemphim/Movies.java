package datvexemphim;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Movies {
    private String namemovies;
    private String timemovies;
    private boolean movies;
    public static StoredFilesMovies movie = new StoredFilesMovies("movies.json");
    
    public Movies() {
        this.namemovies = null;
        this.timemovies = null;
        this.movies = false;
        movie.read();
    }

    public Movies(String namemovies, String timemovies) {
        this.namemovies = namemovies;
        this.timemovies = timemovies;
        this.movies = false;
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

    // public static void searchMovies(String namemovies) {
    //     List<Object> listCheck;
        
    //     listCheck = moviesValid(namemovies);

    //     if (!(boolean) listCheck.get(0)) {
    //         System.out.println(listCheck.get(1));
    //     } else {
    //         System.out.println(listCheck.get(1));
    //         System.out.println(listCheck.get(2));
    //         System.out.println(listCheck.get(3));
    //     }
    // }
    
    // public static List<Object> moviesValid(String namemovies) {
    //     List<Object> list = new ArrayList<>();
    //     JsonArray tempMemory = movie.getAll();
    //     int index = 0;
    //     index = movie.search("mv", namemovies);

    //     if (index != -1) {
    //         list.add(true);// 0
    //         list.add("[MOVIES EXIST] Movies name was found.");// 1
    //         JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
    //         String mv = jsonObject.get("mv").getAsString();
    //         String tmv = jsonObject.get("tmv").getAsString();
    //         list.add(mv);//2
    //         list.add(tmv); //3
    //         return list;
    //     }else {
    //         list.add(false);
    //         list.add("[NO MOVIES] Movies name doesn't exist.");            
    //     }
    //     return list;
    // }

    private void setMovies(String namemovies, String timemovies) {
        this.namemovies = namemovies;
        this.timemovies = timemovies;
        this.movies = true;
    }

    public void moviesName() {
        JsonArray tempMemory = movie.getAll();
        for (int i = 0; i < tempMemory.size(); i++) {
            JsonObject jsonObject = tempMemory.get(i).getAsJsonObject();
            System.out.println(jsonObject.get("mv").getAsString());
            System.out.println(jsonObject.get("tmv").getAsString());
        }
    }

    public void viewMovies(String namemovies) {
        JsonArray tempMemory = movie.getAll();

        int index = -1;
        index = movie.search("mv", namemovies);
        if (index != -1) {
            JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
            String time = jsonObject.get("tmv").getAsString();
            setMovies(namemovies, time);
            System.out.println("Movies has been selected successfully");
        }else {
            System.out.println("Choose movies failed");
        }
    }
}

