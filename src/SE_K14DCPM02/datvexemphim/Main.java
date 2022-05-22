package datvexemphim;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Account accObject = new Account();
        Movies mvObject = new Movies();
        Seats seatObject = new Seats();
        // UITerminal uiTerminal = new UITerminal(accObject);
        BootStrap bootStrap = new BootStrap(accObject, mvObject, seatObject);

        System.out.println("Welcome to the Cinema Reservation System!!\n (To exit type 'exit') \n");

        while(true){
            Thread.sleep(3000);
            bootStrap.displayOptions();
            System.out.println(bootStrap.getPrompt());
            //chon
            //command
            String rep = UITerminalAccounts.scanner.nextLine();

            //LI
            String cmd =  bootStrap.handleCommands(rep);

            if(cmd != null && !cmd.equals("Unkown command.")){
                System.out.println(cmd);
                bootStrap.handleInputs();
            }
        }
    }
}
