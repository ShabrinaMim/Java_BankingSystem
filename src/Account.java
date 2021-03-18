public class Account {

    private String accountType;
    private double depositAmount;
    private double interestRateOnLoan = 10;
    private double interestRateOnDeposit;
    private int accountYearCount = 0;

    public Account(String accountType, double depositAmount) {
        this.accountType = accountType;
        this.depositAmount = depositAmount;
        if (accountType.equals("Student")) {
            this.interestRateOnDeposit = 5;
        } else if (accountType.equals("Savings")) {
            this.interestRateOnDeposit = 10;
        } else if (accountType.equals("Fixed Deposit")) {
            this.interestRateOnDeposit = 15;
        }
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount += depositAmount;
    }

    public int getAccountYearCount() {
        return accountYearCount;
    }

    public void setAccountYearCount(int accountYearCount) {
        this.accountYearCount = accountYearCount;
    }

    public double getInterestRateOnLoan() {
        return interestRateOnLoan;
    }

    public void setInterestRateOnLoan(double interestRateOnLoan) {
        this.interestRateOnLoan = interestRateOnLoan;
    }

    public double getInterestRateOnDeposit() {
        return interestRateOnDeposit;
    }

    public void setInterestRateOnDeposit(double interestRateOnDeposit) {
        this.interestRateOnDeposit = interestRateOnDeposit;
    }

    public void withdrawAmount(double amount) {
        this.depositAmount -= amount;
    }
}
