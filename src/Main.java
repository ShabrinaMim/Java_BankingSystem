import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        while (true) {
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            String[] commands = str.split(" ");
            if (commands[0].equals("Create")) {
                bank.createUser(commands);
            }
            if (commands[0].equals("Deposit")) {
                bank.depositToUser(commands);
            }
            if (commands[0].equals("Withdraw")) {
                bank.withdrawToUser(commands);
            }
            if (commands[0].equals("Query")) {
                bank.queryUser();
            }
            if (commands[0].equals("Request")) {
                bank.requestLoan(commands);
            }
            if (commands[0].equals("Open")) {
                bank.openCommand(commands);
            }
            if (commands[0].equals("Approve")) {
                bank.approveLoan(commands);
            }
            if (commands[0].equals("Change")) {
                bank.changeCommand(commands);
            }
            if (commands[0].equals("Lookup")) {
                bank.lookupCommand(commands);
            }
            if (commands[0].equals("See")) {
                bank.seeCommand(bank);
            }
            if (commands[0].equals("Close")) {
                bank.closeCommand(commands);
            }
            if (commands[0].equals("INC")) {
                bank.incrementCommand();
            }
        }
    }
}
