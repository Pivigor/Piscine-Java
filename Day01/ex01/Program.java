public class Program {
    public static void main(String[] args) {
        User user = new User("Vlad", 5);
        System.out.printf("%d %s %d\n",
                user.getId(),
                user.getName(),
                user.getBalance());

        User user1 = new User("Vladimir", 10);
        System.out.printf("%d %s %d\n",
                user1.getId(),
                user1.getName(),
                user1.getBalance());


        User user2 = new User("Victor", 100);
        System.out.printf("%d %s %d\n",
                user2.getId(),
                user2.getName(),
                user2.getBalance());

        System.out.println(user.getId() + " " + user1.getId() + " " + user2.getId());
    }
}
