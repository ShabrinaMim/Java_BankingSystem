public class LookUpResponsibility {

    public static void lookUpUser(String command) {
        for (int i = 0; i < Bank.usersList.size(); i++) {
            User user = Bank.usersList.get(i);
            if (user.getUserName().equals(command)) {
                System.out.print(user.getUserName() + "’s current balance " + user.getDepositAmount() + "$\n");
                return;
            }
        }
    }
}
