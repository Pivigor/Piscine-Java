public class User {
    public User(String name, int balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Balance cannot be negative");
        }

        this.id = UserIdsGenerator.getInstance().generateId();
        this.name = name;
        this.balance = balance;
        this.transactions = new TransactionsLinkedList();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    private final int id;
    private String name;
    private int balance;
    public TransactionsList transactions;
}
