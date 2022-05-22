package datvexemphim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UITerminalMovies {
    private Movies mvObject;
    Scanner scanner = new Scanner(System.in);

    public UITerminalMovies() {
    }

    public UITerminalMovies(Movies mvObject) {
        this.mvObject = mvObject;
    }

    public List<Object> moviesInput() {
        List<Object> list = new ArrayList<>();
        System.out.println("NAME MOVIES: ");
        String name = scanner.nextLine();
        list.add(name);
        return list;
    }
}
