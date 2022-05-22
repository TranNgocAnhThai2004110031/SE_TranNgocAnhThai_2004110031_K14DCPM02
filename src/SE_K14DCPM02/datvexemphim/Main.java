package datvexemphim;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner inpuScanner  = new Scanner(System.in);
        Account accObject = new Account();
        Movies mvObject;
        // UITerminal uiTerminal = new UITerminal(accObject);
        BootStrap bootStrap = new BootStrap(accObject);

        System.out.println("Welcome to the Cinema Reservation System!!\n (To exit type 'exit') \n");

        while(true){
            Thread.sleep(5000);
            bootStrap.displayOptions();
            System.out.println(bootStrap.getPrompt());
            //chon
            //command
            String rep = inpuScanner.nextLine();

            //LI
            String cmd =  bootStrap.handleCommands(rep);

            if(cmd != null && !cmd.equals("Unkown command.")){
                System.out.println(cmd);
                bootStrap.handleInputs();
            }
            //inpuScanner.nextLine();

           
           
           
            

        }
    }
}
