import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    public TransactionsLinkedList() {
        begin = null;
        end = null;
        sz = 0;
    }

    @Override
    public void add(Transaction transaction) {
        if (sz == 0) {
            begin = new Node(transaction);
            end = begin;
        } else {
            end.next = new Node(transaction);
            end = end.next;
        }
        sz++;
    }

    @Override
    public void removeById(UUID id) {
        Node it = begin;
        Node prev = null;
        while (it != null) {
            if (it.content.getId().equals(id)) {
                if (it == begin) {
                    begin = it.next;
                }
                if (it == end) {
                    end = prev;
                }
                if (prev != null) {
                    prev.next = it.next;
                }
                sz--;
                return;
            }
            prev = it;
            it = it.next;
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        Transaction[] transactions = new Transaction[sz];
        Node it = begin;
        for (int i = 0; i < sz; i++) {
            transactions[i] = it.content;
            it = it.next;
        }
        return transactions;
    }

    private static class Node {
        public Node(Transaction content) {
            this.content = content;
            this.next = null;
        }

        public Transaction content;
        public Node next;
    }

    public static class TransactionNotFoundException extends RuntimeException {
        public TransactionNotFoundException() {
            super("ID not found");
        }
    }

    private Node begin;
    private Node end;
    private int sz;
}
