public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();

        User vlad = new User("Vlad", 5);
        User vladimir = new User("Vladimir", 10);
        User victor = new User("Victor", 100);

        service.addUser(vlad);
        service.addUser(vladimir);
        service.addUser(victor);

        System.out.println("--- DEFAULT VALUES ---");
        System.out.println("Vlad balance: " + service.userBalance(vlad.getId()));
        System.out.println("Vladimir balance: " + service.userBalance(vladimir.getId()));
        System.out.println("Victor balance: " + service.userBalance(victor.getId()));

        service.performTransaction(vlad.getId(), victor.getId(), 40);
        service.performTransaction(vlad.getId(), victor.getId(), -20);
        service.performTransaction(victor.getId(), vladimir.getId(), -10);

        System.out.println();
        System.out.println("--- PERFORMING TRANSFERS ---");
        System.out.println("Vlad balance: " + service.userBalance(vlad.getId()));
        System.out.println("Vladimir balance: " + service.userBalance(vladimir.getId()));
        System.out.println("Victor balance: " + service.userBalance(victor.getId()));

        service.removeTransaction(service.findUserTransfers(victor.getId())[0].getId(), victor.getId());
        service.removeTransaction(service.findUserTransfers(vladimir.getId())[0].getId(), vladimir.getId());

        System.out.println();
        System.out.println("--- CHECK VALIDATION ---");
        Transaction[] transactions = service.checkValidation();
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

        System.out.println();
        System.out.println("--- TESTING EXCEPTION ---");
        try {
            service.performTransaction(vlad.getId(), victor.getId(), 1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        try {
            service.performTransaction(vlad.getId(), victor.getId(), -1000);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
