import java.util.UUID;

public class TransactionsService {

    public TransactionsService() {
        usersList = new UsersArrayList();
    }

    public void addUser(User user) {
        usersList.add(user);
    }

    public int userBalance(int userId) {
        return usersList.findById(userId).getBalance();
    }

    public Transaction performTransaction(int recipientId, int senderId, int transferAmount) {
        if (transferAmount < 0) {
            return new Transaction(usersList.findById(recipientId),
                    usersList.findById(senderId),
                    Transaction.TransferCategory.Credit,
                    transferAmount);
        } else {
            return new Transaction(usersList.findById(recipientId),
                    usersList.findById(senderId),
                    Transaction.TransferCategory.Debit,
                    transferAmount);
        }

    }

    public Transaction[] findUserTransfers(int userId) {
        return usersList.findById(userId).transactions.toArray();
    }

    public void removeTransaction(UUID transactionId, int userId) {
        usersList.findById(userId).transactions.removeById(transactionId);
    }

    public Transaction[] checkValidation() {
        TransactionsList list = new TransactionsLinkedList();
        for (int i = 0; i < usersList.size(); i++) {
            Transaction[] transactions = usersList.findByIndex(i).transactions.toArray();
            for (Transaction transaction : transactions) {
                Transaction[] tests;
                if (transaction.getRecipient() == usersList.findByIndex(i)) {
                    tests = transaction.getSender().transactions.toArray();
                } else {
                    tests = transaction.getRecipient().transactions.toArray();
                }
                if (!findTransactionInArray(tests, transaction)) {
                    list.add(transaction);
                }
            }
        }
        return list.toArray();
    }

    private boolean findTransactionInArray(Transaction[] transactions, Transaction toFind) {
        for (Transaction transaction : transactions) {
            if (transaction == toFind) {
                return true;
            }
        }
        return false;
    }

    private final UsersList usersList;
}
