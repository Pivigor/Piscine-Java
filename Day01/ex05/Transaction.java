import java.util.UUID;

public class Transaction {
    enum TransferCategory {
        Debit,
        Credit
    }

    public Transaction(User recipient, User sender, TransferCategory category, int amount) {
        if (recipient == null || sender == null || category == null) {
            throw new NullPointerException("Arguments cannot be null");
        }

        if (category == TransferCategory.Credit && amount > 0) {
            throw new IllegalArgumentException("Credit cannot be positive");
        }

        if (category == TransferCategory.Debit && amount < 0) {
            throw new IllegalArgumentException("Debit cannot be negative");
        }

        this.id = UUID.randomUUID();
        this.recipient = recipient;
        this.sender = sender;
        this.category = category;
        this.amount = amount;

        if (this.sender.getBalance() - amount >= 0 && this.recipient.getBalance() + amount >= 0) {
            this.sender.setBalance(this.sender.getBalance() - amount);
            this.recipient.setBalance(this.recipient.getBalance() + amount);
            this.sender.transactions.add(this);
            this.recipient.transactions.add(this);
        } else {
            throw new IllegalTransactionException();
        }

    }

    public UUID getId() {
        return id;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public TransferCategory getCategory() {
        return category;
    }

    public int getAmount() {
        return amount;
    }

    public static class IllegalTransactionException extends RuntimeException {
        public IllegalTransactionException() {
            super("Not enough balance to perform the transaction");
        }
    }

    private final UUID id;
    private final User recipient;
    private final User sender;
    private final TransferCategory category;
    private final int amount;
}


