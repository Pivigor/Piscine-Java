public class UsersArrayList implements UsersList {

    public UsersArrayList() {
        users = new User[10];
        sz = 0;
        cap = 10;
    }

    @Override
    public void add(User user) {
        if (sz == cap) {
            cap *= 2;
            User[] temp = users;
            users = new User[cap];
            for (int i = 0; i < sz; i++) {
                users[i] = temp[i];
            }
        }
        users[sz] = user;
        sz++;
    }

    @Override
    public User findById(int id) {
        for (int i = 0; i < sz; i++) {
            if (users[i].getId() == id) {
                return users[i];
            }
        }
        throw new UserNotFoundException();
    }

    @Override
    public User findByIndex(int index) {
        return users[index];
    }

    @Override
    public int size() {
        return sz;
    }

    public static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException() {
            super("ID not found");
        }
    }

    private User[] users;
    private int sz;
    private int cap;
}
