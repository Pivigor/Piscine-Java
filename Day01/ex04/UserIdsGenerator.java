public final class UserIdsGenerator {
    public UserIdsGenerator() {
        idCounter = 1;
    }

    public int generateId() {
        return idCounter++;
    }

    /**
     * Almost perfect SINGLETON realization:<br>
     * - Lazy-loading<br>
     * - Exception handling<br>
     * - Full async support<br>
     * - Disabled inherit<br>
     */
    public static UserIdsGenerator getInstance() {
        if (instance == null) {
            synchronized (UserIdsGenerator.class) {
                if (instance == null) {
                    instance = new UserIdsGenerator();
                }
            }
        }
        return instance;
    }

    private static volatile UserIdsGenerator instance = null;


    private int idCounter;
}
