package edu.school21.chat.models;

import java.util.List;

public class User {
    public User(Long id, String login, String password, List<Chatroom> createdChatrooms, List<Chatroom> usedChatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdChatrooms = createdChatrooms;
        this.usedChatrooms = usedChatrooms;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return String.format("{ User: id=%d; login=%s; password=%s; createdChatrooms=%s; usedChatrooms=%s; }",
                id, login, password, createdChatrooms, usedChatrooms);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getCreatedChatrooms() {
        return createdChatrooms;
    }

    public void setCreatedChatrooms(List<Chatroom> createdChatrooms) {
        this.createdChatrooms = createdChatrooms;
    }

    public List<Chatroom> getUsedChatrooms() {
        return usedChatrooms;
    }

    public void setUsedChatrooms(List<Chatroom> usedChatrooms) {
        this.usedChatrooms = usedChatrooms;
    }

    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdChatrooms;
    private List<Chatroom> usedChatrooms;
}
