public class User extends Account {
    private String userName;
    private double requestedLoan;
    private boolean approveLoan = false;

    public User(String accountType, String userName, double depositAmount) {
        super(accountType, depositAmount);
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getRequestedLoan() {
        return requestedLoan;
    }

    public boolean getApproveLoan() {
        return approveLoan;
    }

    public void setApproveLoan(boolean approveLoan) {
        this.approveLoan = approveLoan;
    }

    public void setRequestedLoan(double requestedLoan) {
        this.requestedLoan = requestedLoan;
    }

    public String toString() {
        return this.getAccountType() + " account for " + this.userName + "Created; Initial Balance " + this.getDepositAmount() + "$\n";
    }
}
