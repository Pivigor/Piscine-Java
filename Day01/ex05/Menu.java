import java.util.Scanner;
import java.util.UUID;

public class Menu {

    enum LaunchMode {
        Production,
        Dev
    }

    public Menu(LaunchMode mode) {
        this.service = new TransactionsService();
        this.mode = mode;
        this.scanner = new Scanner(System.in);
        mainMenu();
    }

    public void mainMenu() {
        while (true) {
            System.out.println("---------------------------------------------------------");
            System.out.println("1. Add a user");
            System.out.println("2. View user balances");
            System.out.println("3. Perform a transfer");
            System.out.println("4. View all transactions for a specific user");
            if (mode == LaunchMode.Dev) {
                System.out.println("5. DEV - remove a transfer by ID");
                System.out.println("6. DEV - check transfer validity");
                System.out.println("7. Finish execution");
            } else {
                System.out.println("5. Finish execution");
            }

            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                System.out.println("No such command");
                continue;
            }
            int input = scanner.nextInt();
            if (input < 1 || (input > 5 && mode == LaunchMode.Production) || input > 7) {
                System.out.println("No such command");
                continue;
            }

            switch (input) {
                case 1:
                    menuAddUser();
                    break;
                case 2:
                    menuUserBalance();
                    break;
                case 3:
                    menuPerformTransfer();
                    break;
                case 4:
                    menuUserTransactions();
                    break;
                case 5:
                    if (mode == LaunchMode.Production) {
                        return;
                    }
                    menuRemoveTransaction();
                    break;
                case 6:
                    menuCheckValidation();
                    break;
                case 7:
                    return;
            }
        }
    }

    private void menuAddUser() {
        while (true) {
            System.out.println("Enter a user name and a balance");

            try {
                String name = scanner.next();
                int balance = scanner.nextInt();
                User user = new User(name, balance);
                service.addUser(user);
                System.out.println("User with id = " + user.getId() + " is added");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuUserBalance() {
        while (true) {
            System.out.println("Enter a user ID");

            try {
                int userId = scanner.nextInt();
                System.out.println(service.findUser(userId).getName() + " - " + service.userBalance(userId));
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuPerformTransfer() {
        while (true) {
            System.out.println("Enter a sender ID, a recipient ID, and a transfer amount");

            try {
                int senderId = scanner.nextInt();
                int recipientId = scanner.nextInt();
                int amount = scanner.nextInt();
                service.performTransaction(recipientId, senderId, amount);
                System.out.println("The transfer is completed");
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuUserTransactions() {
        while (true) {
            System.out.println("Enter a user ID");

            try {
                int userId = scanner.nextInt();
                Transaction[] transactions = service.findUserTransfers(userId);
                for (Transaction transaction : transactions) {
                    if (transaction.getSender() == service.findUser(userId)) {
                        System.out.printf("To %s(id = %d) %+d with id = %s\n",
                                transaction.getRecipient().getName(),
                                transaction.getRecipient().getId(),
                                transaction.getAmount() * -1,
                                transaction.getId());
                    } else {
                        System.out.printf("From %s(id = %d) %+d with id = %s\n",
                                transaction.getSender().getName(),
                                transaction.getSender().getId(),
                                transaction.getAmount(),
                                transaction.getId());
                    }
                }
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuRemoveTransaction() {
        while (true) {
            System.out.println("Enter a user ID and a transfer ID");

            try {
                int userId = scanner.nextInt();
                UUID transactionId = UUID.fromString(scanner.next());
                System.out.println("UUID: " + transactionId);
                Transaction transaction = service.removeTransaction(transactionId, userId);
                System.out.printf("Transfer To %s(id = %d) %d removed\n",
                        transaction.getRecipient().getName(),
                        transaction.getRecipient().getId(),
                        transaction.getAmount());
                return;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void menuCheckValidation() {
        System.out.println("Check results:");

        try {
            Transaction[] transactions = service.checkValidation();
            for (Transaction transaction : transactions) {
                System.out.printf("%s(id = %d) has an unacknowledged transfer id = %s from %s(id = %d) for %d\n",
                        transaction.getRecipient().getName(),
                        transaction.getRecipient().getId(),
                        transaction.getId(),
                        transaction.getSender().getName(),
                        transaction.getSender().getId(),
                        transaction.getAmount());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    LaunchMode mode;
    TransactionsService service;
    Scanner scanner;
}
