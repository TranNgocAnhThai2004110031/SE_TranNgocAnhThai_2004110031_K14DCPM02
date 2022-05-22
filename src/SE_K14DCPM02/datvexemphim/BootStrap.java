/*
*  created date: May 17, 2022
*  author: cgm
*/
package datvexemphim;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BootStrap {
    private String prompt;
    private Actions command;
    private Account accObject;
    private Movies mvObject;
    private Seats seatObject;
    private UITerminalAccounts uiTerminalAccounts = new UITerminalAccounts();
    private UITerminalMovies uiTerminalMovies = new UITerminalMovies(mvObject);
    private UITerminalSeats uiTerminalSeat = new UITerminalSeats(seatObject);

    public BootStrap() {
        this.prompt = null;
        this.command = null;
    }
    
    /**
     * @param accObject
     */
    public BootStrap(Account accObject) {
        this();
        this.accObject = accObject;
    }
    public BootStrap(Movies mvObject) {
        this();
        this.mvObject = mvObject;
    }
    public BootStrap(Seats seatObject) {
        this();
        this.seatObject = seatObject;
    }
    public BootStrap(Account accObject, Movies mvObject) {
        this(accObject);
        this.mvObject = mvObject;
    }
    public BootStrap(Account accObject, Movies mvObject, Seats seatObject) {
        this(accObject, mvObject);
        this.seatObject = seatObject;
    }
    public BootStrap(Actions command) {
        this();
    }       

    /**
     * @return the prompt
     */
    public String getPrompt() {
        return prompt;
    }

    public void displayOptions() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~CRS MENU~~~~~~~~~~~~~~~~~~~");

        // check
        if (!accObject.checkLoggedIn() && !mvObject.checkMovies() && !seatObject.checkSeat()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[VM] View Movies\n" +
                    "[VR] View Reservation\n" +
                    "[RM] Reserve Movies\n" +
                    "[CA] Create Account\n" +
                    "[LI] Login";
        } else if (!accObject.checkLoggedIn() && mvObject.checkMovies() && !seatObject.checkSeat()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[VR] View Reservation\n" +
                    "[CA] Create Account\n" +
                    "[LI] Login";
        // } else if (!accObject.checkLoggedIn() && !mvObject.checkMovies() && seatObject.checkSeat()) {
        //     this.prompt = "Enter one of the commands in the brackets:\n" +
        //             "[RM] Reserve Movies\n" +
        //             "[VM] View Movies\n" +
        //             "[CA] Create Account\n" +
        //             "[LI] Login";            
        } else if (!accObject.checkLoggedIn() && mvObject.checkMovies() && seatObject.checkSeat()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[CA] Create Account\n" +
                    "[LI] Login";
        } else if (accObject.checkLoggedIn() && !mvObject.checkMovies() && !seatObject.checkSeat()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[VM] View Movies\n" +
                    "[VR] View Reservation\n" +
                    "[RM] Reserve Movies\n" +
                    "[LO] Logout";
        } else if (accObject.checkLoggedIn() && mvObject.checkMovies() && !seatObject.checkSeat()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[VR] View Reservation\n" +
                    "[LO] Logout";
        // } else if (accObject.checkLoggedIn() && mvObject.checkMovies() && seatObject.checkSeat()) {
        //     this.prompt = "Enter one of the commands in the brackets:\n" +
        //             "[LO] Logout";
        } else {
            this.prompt = "LOGGED IN AS # " + accObject.getUsername();
            System.out.println("Congratulations on your successful booking\n" +
                    "Enter on of the commands in brackets:\n " +
                    "[LO] Logout");
        }
    }
    
    public void handleInputs() {
        if (this.command.equals(Actions.CA)) {
            Account account = uiTerminalAccounts.createAccountInputs();
            uiTerminalAccounts.createAccount(account.getUsername(), account.getPassword(), account.getEmail());

        }else if (this.command.equals(Actions.LI)) {
           List<Object> list =  uiTerminalAccounts.loginInputs();// Implementations
           accObject.login(list.get(0).toString(), (int)list.get(1));

        }else if(this.command.equals(Actions.LO)){
            accObject.logout();
        }else if (this.command.equals(Actions.VM)) {
            List<Object> list =  uiTerminalMovies.moviesInput();
            uiTerminalMovies.chooseMovies(list.get(0).toString());
            mvObject.viewMovies(list.get(0).toString());
        }else if (this.command.equals(Actions.RM)) {
            mvObject.moviesName();
        }else if (this.command.equals(Actions.VR)) {
            seatObject.emptySeat();
            List<Object> list =  uiTerminalSeat.bookingSeatInputs();
            uiTerminalSeat.bookingSeat(list.get(0).toString(), (Integer)list.get(1));
            seatObject.seatReservation(list.get(0).toString(), (Integer)list.get(1));
        }else if (this.command.equals(Actions.IP)) {
            
        } 
    }

    public String handleCommands(String rep) {
        String cmd = rep.toUpperCase();
        this.command = Actions.valueOf(cmd);

        if (this.command.equals(Actions.CA)) {
            return "Enter a username, a password, a email";
        } else if (this.command.equals(Actions.LO)) {
            return "Logging out ...";
        } else if (this.command.equals(Actions.LI)) {
            return "Enter a username, a password";
        } else if (this.command.equals(Actions.VM)) {
            return "Enter a name movies";
        }else if (this.command.equals(Actions.RM)) {
            return "Movie titles currently showing";
        }else if (this.command.equals(Actions.VR)) {
            return "Enter a row, a colum";
        } else {
            return "Unkown command.";
        }

    }
    
    
}
