/*
*  created date: May 17, 2022
*  author: cgm
*/
package datvexemphim;

import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class BootStrap {
    private String prompt;
    private Actions command;
    private Account accObject;
    private Movies mvObject;
    private UITerminal uiTerminal = new UITerminal();
    private UITerminalMovies uiTerminalMovies = new UITerminalMovies(mvObject);

    public BootStrap() {
        this.prompt = null;
        this.command = null;
    }
    /**
     * @param accObject
     */
    public BootStrap(Account accObject) {
        this.accObject = accObject;
        this.prompt = null;
        this.command = null;
    }
    public BootStrap(Actions command) {
        this.command = null;
        this.prompt = null;
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
        if (!accObject.checkLoggedIn()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[CA] Create Account\n" +
                    "[LI] Login";
        } else {
            this.prompt = "LOGGED IN AS # " + accObject.getUsername();
            System.out.println("Enter on of the commands in brackets:\n " +
                    "[VM] View Movies\n" +
                    "[LO] Logout");
        }

    }
    
    public void handleInputs() {
        if (this.command.equals(Actions.CA)) {
            Account account = UITerminal.createAccountInputs();
            UITerminal.createAccount(account.getUsername(), account.getPassword(), account.getEmail());

        } else if (this.command.equals(Actions.LI)) {
           List<Object> list =  uiTerminal.loginInputs();// Implementations
           accObject.login(list.get(0).toString(), (int)list.get(1));

        }else if(this.command.equals(Actions.LO)){
            accObject.logout();
        }else if (this.command.equals(Actions.VM)) {
            List<Object> list =  uiTerminalMovies.moviesInput();
            Movies.searchMovies(list.get(0).toString());
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
        } else {
            return "Unkown command.";
        }

    }
    
    
}
