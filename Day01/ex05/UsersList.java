public interface UsersList {

    void add(User user);

    User findById(int id);

    User findByIndex(int index);

    int size();

}
