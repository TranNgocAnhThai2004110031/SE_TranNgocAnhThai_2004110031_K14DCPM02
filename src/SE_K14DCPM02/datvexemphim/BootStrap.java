/*
*  created date: May 17, 2022
*  author: cgm
*/
package datvexemphim;

import java.util.List;

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
        this();
        this.accObject = accObject;
    }
    public BootStrap(Movies mvObject) {
        this();
        this.mvObject = mvObject;
    }
    public BootStrap(Account accObject, Movies mvObject) {
        this(accObject);
        this.mvObject = mvObject;
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
        if (!accObject.checkLoggedIn() && !mvObject.checkMovies()) {
            this.prompt = "Enter one of the commands in the brackets:\n" +
                    "[VM] View Movies\n" +
                    "[CA] Create Account\n" +
                    "[LI] Login";
        } else if (!accObject.checkLoggedIn() && mvObject.checkMovies()) {
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
            Account account = uiTerminal.createAccountInputs();
            uiTerminal.createAccount(account.getUsername(), account.getPassword(), account.getEmail());

        } else if (this.command.equals(Actions.LI)) {
           List<Object> list =  uiTerminal.loginInputs();// Implementations
           accObject.login(list.get(0).toString(), (int)list.get(1));

        }else if(this.command.equals(Actions.LO)){
            accObject.logout();
        }else if (this.command.equals(Actions.VM)) {
            mvObject.moviesName();
            List<Object> list =  uiTerminalMovies.moviesInput();
            uiTerminalMovies.searchViewMovies(list.get(0).toString());
            mvObject.viewMovies(list.get(0).toString());
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
