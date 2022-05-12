import java.util.InputMismatchException;
import java.util.Scanner;

public class UITerminal {
    private String prompt;
    private String command;

    private Account accObj = new Account();

    public static Scanner sc = new Scanner(System.in);

    public void display_options() {
        accObj.accounts.read();
        accObj.accounts.update();
        int luaChon;
        do {
            System.out.println("\n\t-------------------------------- Menu --------------------------------");
            System.out.println("\t1. Đăng nhập.");
            System.out.println("\t2. Đăng xuất.");
            System.out.println("\t3. Tạo tài khoản.");
            System.out.println("\t0. Thoát.");
            System.out.println("\t------------------------------------------------------------------------");
            System.out.print("\n- Vui lòng nhập lựa chọn: ");
            luaChon = sc.nextInt();
            sc.nextLine();

            switch (luaChon) {
                case 1:
                    login_inputs();
                    break;
                case 2:
                    accObj.logout();
                    break;
                case 3:
                    create_account_inputs();
                    break;
                default:
                    break;
            }
        } while (luaChon != 0);
        accObj.accounts.write();     
        
    }

    // public void handle_command() {
        
    // }

    // public void handle_inputs() {
        
    // }
    
    // public String getPrompt() {
    //     return prompt;
    // }

    public void login_inputs() {
        System.out.print("User Name: ");
        String un = sc.nextLine();
        System.out.print("Password: ");
        int ps = 0;
        try {
            ps = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Password chỉ bao gồm chữ!!!!!");
            e.printStackTrace();
        }
        sc.nextLine();
        accObj.login(un, ps);      
    }

    public void create_account_inputs() {
        System.out.print("User Name: ");
        String un = sc.nextLine();
        System.out.print("Password: ");
        int ps = 0;
        try {
            ps = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Password chỉ bao gồm chữ!!!!!");
            e.printStackTrace();
        }
        
        sc.nextLine();
        System.out.print("Email: ");
        String email = sc.nextLine();
        for (Account account : accObj.accounts.list) {
            if (un.equals(account.getUsername())) {
                System.out.println("Username đã được sử dụng trước đó.");
                System.out.println("Tạo tài khoản thất bại!!!.");
                return;
            } else if (email.equals(account.getEmail())) {
                System.out.println("Email đã được sử dụng trước đó.");
                System.out.println("Tạo tài khoản thất bại!!!.");
                return;
            }
        }
        accObj.create_account(un, ps, email);  
        System.out.println("Tạo tài khoản thành công.");
    }

    
}
