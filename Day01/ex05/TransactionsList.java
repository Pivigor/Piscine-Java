import java.util.UUID;

public interface TransactionsList {
    void add(Transaction transaction);

    Transaction removeById(UUID id);

    Transaction[] toArray();
}
