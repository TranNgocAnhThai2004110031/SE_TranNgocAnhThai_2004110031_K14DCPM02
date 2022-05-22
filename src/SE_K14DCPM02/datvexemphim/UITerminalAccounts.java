/*
*  created date: May 17, 2022
*  author: cgm
*/
package datvexemphim;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UITerminalAccounts {
    public static Scanner scanner = new Scanner(System.in);
    // private Account accObject;
    // private Movies mvObject;
    private static StoredFilesAccounts storedFiles = new StoredFilesAccounts("accounts.json");
    // private String prompt;
    // private Actions command;

    // /**
    //  * @param accObject
    //  */
    // public UITerminal(Account accObject) {
    //     this.accObject = accObject;
    //     // this.prompt = null;
    //     // this.command = null;
    // }

    // public UITerminal(Movies mvObject) {
    //     this.mvObject = mvObject;
    //     this.prompt = null;
    //     this.command = null;
    // }

    // public void displayOptions() {
    //     System.out.println("~~~~~~~~~~~~~~~~~~~~CRS MENU~~~~~~~~~~~~~~~~~~~");

    //     // check
    //     if (!accObject.checkLoggedIn()) {
    //         this.prompt = "Enter one of the commands in the brackets:\n" +
    //                 "[CA] Create Account\n" +
    //                 "[LI] Login";
    //     } else {
    //         this.prompt = "LOGGED IN AS # " + accObject.getUsername();
    //         System.out.println("Enter on of the commands in brackets:\n " +
    //                 "[VM] View Movies\n" +
    //                 "[LO] Logout");
    //     }

    // }

    // public String handleCommands(String rep) {
    //     String cmd = rep.toUpperCase();
    //     this.command = Actions.valueOf(cmd);

    //     if (this.command.equals(Actions.CA)) {
    //         return "Enter a username, a password, a email";
    //     } else if (this.command.equals(Actions.LO)) {
    //         return "Logging out ...";
    //     } else if (this.command.equals(Actions.LI)) {
    //         return "Enter a username, a password";
    //     } else if (this.command.equals(Actions.VM)) {
    //         return "Enter a name movies";
    //     } else {
    //         return "Unkown command.";
    //     }

    // }

    // /**
    //  * @return the prompt
    //  */
    // public String getPrompt() {
    //     return prompt;
    // }

    // public void handleInputs() {
    //     if (this.command.equals(Actions.CA)) {
    //         Account account = createAccountInputs();
    //         createAccount(account.getUsername(), account.getPassword(), account.getEmail());

    //     } else if (this.command.equals(Actions.LI)) {
    //        List<Object> list =  loginInputs();// Implementations
    //        accObject.login(list.get(0).toString(), (int)list.get(1));

    //     }else if(this.command.equals(Actions.LO)){
    //         accObject.logout();
    //     }
    //     // else if (this.command.equals(Actions.VM)) {
    //     //     List<Object> list =  moviesInput();
    //     //     Movies.searchMovies(list.get(0).toString());
    //     // }
    // }

    public List<Object> loginInputs() {
        List<Object> list = new ArrayList<>();
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();
        System.out.print("PASSWORD: ");
        Integer password = scanner.nextInt();scanner.nextLine();
        list.add(username);
        list.add(password);
        return list;
    }

    // hanh vi cuar object
    public void createAccount(String username, Integer password, String email) {
        // so do tuan tu - sequence
        // check valid username, email =>??? class method : accountValid
        List<Object> listCheck;
        
        listCheck = accountValid(username, email);

        if (!(boolean) listCheck.get(0)) {
            System.out.println(listCheck.get(1));
        } else {
            // them account moi vao CSDL
            storedFiles.update(username, password, email);// memory
            storedFiles.write();
            storedFiles.read();
            System.out.println(listCheck.get(1));
        }

        // if(!valid){
        // /
        // }else{
        // tao tai khoan => CSDL
        // }

    }

    public Account createAccountInputs() {
        System.out.print("USERNAME: ");
        String username = scanner.nextLine();
        System.out.print("PASSWORD: ");
        Integer password = scanner.nextInt();
        scanner.nextLine();
        System.out.print("EMAIL: ");
        String email = scanner.nextLine();
        return new Account(username, password, email);
    }

    private static List<Object> accountValid(String username, String email) {
        List<Object> list = new ArrayList<>();
        int index = -1;
        int index2 = -1;
        index = storedFiles.search("un", username);

        if (index != -1) {
            // valid = false;
            list.add(false);// 0
            list.add("[USERNAME EXISTS] An user with that username already exists.");// 1
            return list;
        }

        index2 = storedFiles.search("email", email);
        if (index2 != -1) {
            // valid = false;
            list.add(false);
            list.add("[EMAIL EXISTS] A mail with that email already exists.");
            return list;
        }

        if (index == -1) {
            // valid
            list.add(true);
            list.add("[ACCOUNT_CREATED] An account has been created.");
        }
        return list;
    } 

}
