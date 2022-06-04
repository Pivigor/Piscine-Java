public class Program {
    public static void main(String[] args) {
        UsersArrayList list = new UsersArrayList();
        User user = new User("Vlad", 5);
        User user1 = new User("Vladimir", 10);
        User user2 = new User("Victor", 100);

        list.add(user);
        list.add(user1);
        list.add(user2);

        System.out.println("--- TESTING SEARCH ---");
        System.out.println(list.findByIndex(0).getName());
        System.out.println(list.findById(1).getName());

        System.out.println();
        System.out.println("--- TESTING RESIZE ---");
        for (int i = 0; i < 100; i++) {
            list.add(new User("Vlad " + i, 5));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.findByIndex(i).getName());
        }

        System.out.println();
        System.out.println("--- TESTING EXCEPTION ---");
        try {
            System.out.println(list.findById(1000).getName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
