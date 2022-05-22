package datvexemphim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UITerminalSeats {
    private Seats seatObject = new Seats();
    public static Scanner scanner = new Scanner(System.in);
    
    public UITerminalSeats() {
    }
    public UITerminalSeats(Seats seatObject) {
        this.seatObject = seatObject;
    }

    public List<Object> bookingSeatInputs(){
        List<Object> list = new ArrayList<>();
        System.out.println("ROW(A TO F): ");
        String row = scanner.nextLine();
        System.out.println("COLUMN(1 to 20): ");
        Integer column = scanner.nextInt();
        list.add(row);
        list.add(column);
        return list;
    }

    public void bookingSeat(String row, Integer column){
        List<Object> listcheck;
        listcheck = seatValid(row, column);

        if(!(boolean) listcheck.get(0)){
            System.out.println(listcheck.get(1));
        }else{
            System.out.println(listcheck.get(1));
            System.out.println(listcheck.get(2) + " " + listcheck.get(3));
        }
    }

    private List<Object> seatValid(String row, Integer column){
        List<Object> list = new ArrayList<>();
        JsonArray tempMemory = Seats.seats.getAll();
        int index = -1;
        int index2 = -1;
        index = Seats.seats.searchRow("r", row);
        index2 = Seats.seats.searchColumn("cl", column);

        if(index != -1 && index2 != -1){
            JsonObject jsonObject = tempMemory.get(index).getAsJsonObject();
            String rowss = jsonObject.get("r").getAsString();
            int columss = jsonObject.get("cl").getAsInt();
            list.add(true);
            list.add("[BOOKING SEAT FAIL] the seat are booked already by someone !");
            list.add(rowss);
            list.add(columss);
            return list;
        }
        if(index == -1){
            list.add(false);
            list.add("[THE SEAT ARE BOOKED] the seat has been booked !");
        }
        return list;
    }
}
