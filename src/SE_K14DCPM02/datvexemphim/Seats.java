package datvexemphim;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Seats {
    private String row;
    private Integer column;
    private boolean seat;

    public static StoredFileSeat seats = new StoredFileSeat("seats.json");

    public Seats(){
        this.row = null;
        this.column = null;
        this.seat = false;
        seats.read();
    }

    public Seats(String row, Integer column) {
        this.row = row;
        this.column = column;
        this.seat = false;
    }

    

    // public static List<Object> seatValid(String row, Integer column){
    //     List<Object> list = new ArrayList<>();
    //     int index = 0;
    //     int index2 = 0;
    //     index = seats.searchRow("row", row);
    //     index2 = seats.searchColumn("column", column);

    //     if(index != -1 && index2 != -1){
    //         list.add(false);
    //         list.add("[BOOKING SEAT FAIL] the seat are booked already by someone !");
    //         return list;
    //     }
    //     if(index == 1){
    //         list.add(true);
    //         list.add("[THE SEAT ARE BOOKED] the seat has been booked !");
    //     }
    //     return list;
    // }

    public void emptySeat() {
        JsonArray tempMemory = seats.getAll();
        for (int i = 0; i < tempMemory.size(); i++) {
            JsonObject jsonObject = tempMemory.get(i).getAsJsonObject();
            System.out.print(jsonObject.get("r").getAsString());
            System.out.println(" " + jsonObject.get("cl").getAsString());
        }
    }

    // public static void bookingSeat(String row, Integer column){
    //     List<Object> listcheck;
    //     listcheck = seatValid(row, column);

    //     if(!(boolean) listcheck.get(0)){
    //         System.out.println(listcheck.get(1));
    //     }else{
    //         seats.update(row, column);
    //         seats.write();
    //         System.out.println(listcheck.get(1));
    //     }
    // }

    public String getRow() {
        return row;
    }

    public Integer getColumn() {
        return column;
    }

    public boolean getSeat() {
        return seat;
    }

    private void setSeat(String row, Integer colum) {
        this.row = row;
        this.column = colum;
        this.seat = true;
    }

    public boolean checkSeat() {
        return seat;
    }

    public void seatReservation(String row, Integer colum) {
        int index = -1;
        int index2 = -1;
        index = seats.searchRow("r", row);
        index2 = seats.searchColumn("cl", colum);
        if (index != -1 && index2 != -1) {
            setSeat(row, colum);
            System.out.println("Seat has been selected successfully");
        } else {
            System.out.println("Choose seat failed");
        }

    }    
}
