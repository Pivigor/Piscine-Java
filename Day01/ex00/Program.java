public class Program {
    public static void main(String[] args) {
        System.out.println("--- TESTING USER ---");
        User user = new User("Vlad", 10);
        System.out.printf("%d %s %d\n",
                user.getId(),
                user.getName(),
                user.getBalance());
        user.setName("Vladimir");
        System.out.printf("%d %s %d\n",
                user.getId(),
                user.getName(),
                user.getBalance());

        User user1 = new User("Victor", 100);
        System.out.printf("%d %s %d\n",
                user1.getId(),
                user1.getName(),
                user1.getBalance());

        try {
            User user2 = new User("Victor", -100);
            System.out.printf("%d %s %d\n",
                    user2.getId(),
                    user2.getName(),
                    user2.getBalance());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        System.out.println();

        System.out.println("--- TESTING TRANSACTION ---");
        Transaction transaction = new Transaction(user1, user, Transaction.TransferCategory.Debit, 10);
        System.out.printf("%s %s-%s %s-%s %s %d\n",
                transaction.getId(),
                transaction.getRecipient(),
                transaction.getRecipient().getName(),
                transaction.getSender(),
                transaction.getSender().getName(),
                transaction.getCategory(),
                transaction.getAmount());
        System.out.printf("%d %s %d\n",
                user.getId(),
                user.getName(),
                user.getBalance());
        System.out.printf("%d %s %d\n",
                user1.getId(),
                user1.getName(),
                user1.getBalance());

        Transaction transaction1 = new Transaction(user1, user, Transaction.TransferCategory.Credit, -50);
        System.out.printf("%s %s-%s %s-%s %s %d\n",
                transaction1.getId(),
                transaction1.getRecipient(),
                transaction1.getRecipient().getName(),
                transaction1.getSender(),
                transaction1.getSender().getName(),
                transaction1.getCategory(),
                transaction1.getAmount());
        System.out.printf("%d %s %d\n",
                user.getId(),
                user.getName(),
                user.getBalance());
        System.out.printf("%d %s %d\n",
                user1.getId(),
                user1.getName(),
                user1.getBalance());


        try {
            Transaction transaction2 = new Transaction(null, null, null, 0);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            Transaction transaction3 = new Transaction(user, user1, Transaction.TransferCategory.Debit, -10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        try {
            Transaction transaction4 = new Transaction(user, user1, Transaction.TransferCategory.Credit, 10);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}
