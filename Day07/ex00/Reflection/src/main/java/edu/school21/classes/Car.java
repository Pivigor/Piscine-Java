package edu.school21.classes;

public class Car {
    private Integer id;
    private User owner;

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", owner=" + owner +
                '}';
    }

    private void doNothing() {
        String nothing = "Its nothing";
    }

    private String doSomething(String start, int stop) {
        return start + stop;
    }
}
