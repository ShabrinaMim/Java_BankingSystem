import java.util.ArrayList;

public class Bank {
    private double internalFund = 1000000;
    private int yearCount = 0;
    public static User currentUser = null;
    public static Officer currentOfficer = null;
    public static Cashier currentCashier = null;


    public static ArrayList<User> usersList = new ArrayList<>();
    ;
    public static ArrayList<Cashier> cashiersList = new ArrayList<>();
    ;
    public static ArrayList<Officer> officersList = new ArrayList<>();
    public static ArrayList<User> requestsList = new ArrayList<>();
    private ManagingDirector currentMD;
    ;
    public static int currentEmployee = -1; //0 for MD,1 for Officer,2 for Cashier

    public Bank() {
        currentMD = new ManagingDirector("MD");
        Officer S1 = new Officer("S1");
        Officer S2 = new Officer("S2");
        officersList.add(S1);
        officersList.add(S2);
        Cashier C1 = new Cashier("C1");
        Cashier C2 = new Cashier("C2");
        Cashier C3 = new Cashier("C3");
        Cashier C4 = new Cashier("C4");
        Cashier C5 = new Cashier("C5");
        cashiersList.add(C1);
        cashiersList.add(C2);
        cashiersList.add(C3);
        cashiersList.add(C4);
        cashiersList.add(C5);
        System.out.println("Bank Created; MD, S1, S2, C1, C2, C3, C4, C5 created");
    }

    public double getInternalFund() {
        return internalFund;
    }

    public void createUser(String[] commands) {
        double depositAmount = Double.parseDouble(commands[3]);
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            if (user.getUserName().equals(commands[1])) {
                System.out.println("Account can not be created; Same username");
                return;
            }
        }
        if (commands[2].equals("Fixed Deposit") && depositAmount < 1000000) {
            System.out.print("Account can not be created\n");
            return;
        }
        User newUser = new User(commands[1], commands[2], depositAmount);
        usersList.add(newUser);
        System.out.print(newUser);
        currentUser = newUser;
    }

    public void depositToUser(String[] commands) {
        double depositToAmount = Double.parseDouble(commands[1]);
        if (currentUser.getAccountType().equals("Fixed Deposit") && depositToAmount < 500000) {
            System.out.println("Deposit can happen");
            return;
        }
        currentUser.setDepositAmount(depositToAmount);
        System.out.println(commands[1] + "$ deposited; current balance " + currentUser.getDepositAmount() + "$");
    }

    public void withdrawToUser(String[] commands) {
        double withdrawAmount = Double.parseDouble(commands[1]);
        if (currentUser.getAccountType().equals("Student") && withdrawAmount > 10000.0) {
            System.out.println("Invalid transaction; current balance " + currentUser.getDepositAmount() + "$");
            return;
        }
        if (currentUser.getAccountType().equals("Fixed Deposit") && currentUser.getAccountYearCount() < 1) {
            System.out.println("Invalid transaction; One year is not completed for Fixed Deposit; current balance " + currentUser.getDepositAmount() + "$");
            return;
        }
        if (currentUser.getAccountType().equals("Savings") && (currentUser.getDepositAmount() - withdrawAmount) < 1000) {
            System.out.println("Invalid transaction for Savings Account; current balance " + currentUser.getDepositAmount() + "$");
            return;
        }
        if ((currentUser.getDepositAmount() - withdrawAmount) < 0) {
            System.out.println("Withdraw amount is greater than Deposit");
            return;
        }
        currentUser.withdrawAmount(withdrawAmount);
        System.out.println(commands[1] + "$ withdrawn; current balance " + currentUser.getDepositAmount() + "$");
    }

    public void queryUser() {
        System.out.print("Current balance " + currentUser.getDepositAmount() + "$, ");
        if (currentUser.getApproveLoan()) {
            System.out.println("loan " + currentUser.getRequestedLoan());
            return;
        }
    }

    public void requestLoan(String[] commands) {
        double requestedLoan = Double.parseDouble(commands[1]);
        currentUser.setRequestedLoan(requestedLoan);
        requestsList.add(currentUser);
        System.out.println("Loan request successful, sent for approval");
    }

    public void openCommand(String[] commands) {
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            if (user.getUserName().equals(commands[1])) {
                currentUser = user;
                System.out.println("Welcome back, " + currentUser.getUserName());
                return;
            }
        }
        for (int i = 0; i < officersList.size(); i++) {
            Officer officer = officersList.get(i);
            if (officer.getOfficerName().equals(commands[1])) {
                currentOfficer = officer;
                System.out.println(currentOfficer.getOfficerName() + " active, there are loan approvals pending");
                currentEmployee = 1;
                return;
            }
        }
        for (int i = 0; i < usersList.size(); i++) {
            Cashier cashier = cashiersList.get(i);
            if (cashier.getCashierName().equals(commands[1])) {
                currentCashier = cashier;
                System.out.println(currentCashier.getCashierName() + " active, there are loan approvals pending");
                currentEmployee = 2;
                return;
            }
        }
        if (commands[1].equals("MD")) {
            System.out.println(currentMD.getManagingDirectorName() + " active, there are loan approvals pending");
            currentEmployee = 0;
            return;
        }
    }

    public void approveLoan(String[] commands) {
        if (currentEmployee == 0) {
            ApproveLoanResponsibility.approveLoanOfUser();
        } else if (currentEmployee == 1) {
            ApproveLoanResponsibility.approveLoanOfUser();
        } else {
            System.out.println("You don’t have permission for this operation");
        }
    }

    public void changeCommand(String[] commands) {
        if (currentEmployee == 0) {
            currentMD.changeInterestRate(commands);
        } else {
            System.out.println("You don’t have permission for this operation");
        }
    }

    public void lookupCommand(String[] commands) {
        if (currentEmployee == 0 || currentEmployee == 1 || currentEmployee == 2) {
            LookUpResponsibility.lookUpUser(commands[1]);
        }
    }

    public void seeCommand(Bank bank) {
        if (currentEmployee == 0) {
            currentMD.seeInternalFund(bank);
        } else {
            System.out.println("You don’t have permission for this operation");
        }
    }

    public void closeCommand(String[] commands) {
        if (currentUser != null) {
            System.out.println("Transaction Closed for " + currentUser.getUserName());
            currentUser = null;
            return;
        }
        if (currentEmployee == 0) {
            System.out.println("Operations for MD closed");
            currentEmployee = -1;
        } else if (currentEmployee == 1) {
            System.out.println("Operations for " + currentOfficer.getOfficerName() + " closed");
            currentOfficer = null;
            currentEmployee = -1;
        } else if (currentEmployee == 2) {
            currentCashier = null;
            System.out.println("Operations for " + currentCashier.getCashierName() + " closed");
            currentEmployee = -1;
        }
    }

    public void incrementCommand() {
        this.yearCount++;
        System.out.println(this.yearCount+" year passed");
        for (int i = 0; i < usersList.size(); i++) {
            User user = usersList.get(i);
            user.setAccountYearCount(user.getAccountYearCount() + 1);

            if (user.getApproveLoan()) {
                double val = user.getRequestedLoan()*(10.0 / 100.0);
                user.withdrawAmount(val);
            }

            if (user.getAccountType().equals("Savings") || user.getAccountType().equals("Fixed Deposit")) {
                user.withdrawAmount(500);
            }

            if (user.getAccountType().equals("Student")) {
                double val = user.getDepositAmount()*(5.0 / 100.0);
                user.setDepositAmount(val);
            } else if (user.getAccountType().equals("Savings")) {
                double val = user.getDepositAmount()*(10.0 / 100.0);
                user.setDepositAmount(val);
            } else if (user.getAccountType().equals("Fixed Deposit")) {
                double val = user.getDepositAmount()*(15.0 / 100.0);
                user.setDepositAmount(val);
            }
        }
    }
}

