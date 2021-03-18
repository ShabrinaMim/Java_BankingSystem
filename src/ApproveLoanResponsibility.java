public class ApproveLoanResponsibility {

    public static void approveLoanOfUser() {
        for (int i = 0; i < Bank.requestsList.size(); i++) {
            User user = Bank.requestsList.remove(i);
            if (user.getAccountType().equals("Student") && user.getRequestedLoan() <= 1000) {
                System.out.print("Loan for " + user.getUserName() + " approved\n");
                user.setApproveLoan(true);
                user.setDepositAmount(user.getRequestedLoan());
            } else if (user.getAccountType().equals("Savings") && user.getRequestedLoan() <= 10000) {
                System.out.print("Loan for " + user.getUserName() + " approved\n");
                user.setApproveLoan(true);
                user.setDepositAmount(user.getRequestedLoan());
            } else if (user.getAccountType().equals("Fixed deposit") && user.getRequestedLoan() <= 1000000) {
                System.out.print("Loan for " + user.getUserName() + " approved\n");
                user.setApproveLoan(true);
                user.setDepositAmount(user.getRequestedLoan());
            } else {
                System.out.print("Loan can not be approved\n");
            }
        }
    }
}
