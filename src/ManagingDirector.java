public class ManagingDirector extends MainResponsibilities {
    private String managingDirectorName;

    public ManagingDirector(String managingDirectorName) {
        this.managingDirectorName = managingDirectorName;
    }

    public String getManagingDirectorName() {
        return managingDirectorName;
    }

    public void setManagingDirectorName(String managingDirectorName) {
        this.managingDirectorName = managingDirectorName;
    }

    @Override
    void changeInterestRate(String[] commands) {
        double interestRateOnDeposit = Double.parseDouble(commands[2]);
        Bank.currentUser.setInterestRateOnDeposit(interestRateOnDeposit);
    }

    @Override
    void seeInternalFund(Bank bank) {
        System.out.print("Internal Fund is " + bank.getInternalFund() + "\n");
    }
}
