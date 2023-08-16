import java.util.Arrays;
import java.util.Scanner;

public class CliAssignment2 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "👷 Welcome To Smart Banking System";
        final String ADD_ACCOUNT = "➕ Add New Account";
        final String DEPOSIT = "➕ Deposit";
        final String WITHDRAW = "➕ Withdraw";
        final String TRANSFER = "➕ Transfer";
        final String PRINT_BALANCE = "🖨 Print Balance";
        final String DELETE_ACCOUNT = "❌ Remove Exisiting Account";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[][] account = new String[0][];
        // int[] ID = new int[0];
        // int[] DEPO = new int[0];
        String screen = DASHBOARD;

        do {
            final String APP_TITLE = String.format("%s%s%s",
                    COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD:
                    System.out.println("\t[1]. Open New Account");
                    System.out.println("\t[2]. Deposit Money");
                    System.out.println("\t[3]. Withdraw Money");
                    System.out.println("\t[4]. Transfer Money");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Drop Existing Account");
                    System.out.println("\t[7]. Exit");
                    System.out.print("\tEnter an option to continue: ");

                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = ADD_ACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAW;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = PRINT_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;
                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:
                    }
                    break;

                case ADD_ACCOUNT:
                    // int id = account.length;
                    String name;
                    int deposit;
                    boolean valid;

                    // set ID

                    String id = String.format("\tNew A/C ID: SDB-%05d \n", (account.length + 1));
                    // System.out.printf("\tNew A/C ID: SDB-%05d \n", (account.length + 1));


                    // Name Validation
                    do {
                        valid = true;
                        System.out.print("\tEnter A/C Name: ");
                        name = SCANNER.nextLine().strip();
                        if (name.isBlank()) {
                            System.out.printf(ERROR_MSG, "A/C name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if (!(Character.isLetter(name.charAt(i)) ||
                                    Character.isSpaceChar(name.charAt(i)))) {
                                System.out.printf(ERROR_MSG, "Invalid A/C name");
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);



                    // // Initial Deposit

                    do {
                        System.out.println();
                        System.out.print("Enter your Deposited Amount Here :");
                        deposit = SCANNER.nextInt();
                        SCANNER.nextLine();

                        if (deposit > 5000) {
                            System.out.println("Initial Deposit :" + deposit);
                            System.out.println();
                            System.out.printf(SUCCESS_MSG,
                                    String.format("%s:%s has been saved successfully", account.length, name));
                        } else {

                            System.out.printf(ERROR_MSG, "Not Sufficient Amount In Your A/C");
                        }
                    } while (!valid);

                    String[][] newAccount = new String[account.length + 1][3];
                    for (int i = 0; i < account.length; i++) {
                        newAccount[i] = account[i];
                    }

                    newAccount[newAccount.length - 1][0] = id;
                    newAccount[newAccount.length - 1][1] = name;
                    newAccount[newAccount.length - 1][2] = deposit + "";

                    account = newAccount;

                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().toUpperCase().equals("Y"))
                        continue;
                    ;
                    screen = DASHBOARD;
                    break;
            }
        } while (true);

    }
}