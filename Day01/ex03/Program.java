public class Program {
    public static void main(String[] args) {
        User user = new User("Vlad", 5);
        User user1 = new User("Vladimir", 10);
        User user2 = new User("Victor", 100);

        Transaction transaction = new Transaction(user1, user2, Transaction.TransferCategory.Debit, 30);
        Transaction transaction1 = new Transaction(user1, user2, Transaction.TransferCategory.Credit, -20);
        Transaction transaction2 = new Transaction(user, user2, Transaction.TransferCategory.Debit, 20);

        System.out.println("--- TESTING ADDING ---");
        printTransactions(user);
        printTransactions(user1);
        printTransactions(user2);

        user.transactions.removeById(transaction2.getId());
        user1.transactions.removeById(transaction.getId());
        user2.transactions.removeById(transaction1.getId());

        System.out.println();
        System.out.println("--- TESTING REMOVING ---");
        printTransactions(user);
        printTransactions(user1);
        printTransactions(user2);
    }

    public static void printTransactions(User user) {
        Transaction[] transactions = user.transactions.toArray();
        System.out.println("User " + user.getName() + " transactions:");
        for (Transaction transaction : transactions) {
            System.out.printf("%s %s-%s %s-%s %s %d\n",
                    transaction.getId(),
                    transaction.getRecipient(),
                    transaction.getRecipient().getName(),
                    transaction.getSender(),
                    transaction.getSender().getName(),
                    transaction.getCategory(),
                    transaction.getAmount());
        }
    }
}
