package ru.maltsev.api.users.domain;

import java.util.List;

public class UserResponseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    private List<AlbumsResponseModel> albums;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<AlbumsResponseModel> getAlbums() {
        return albums;
    }

    public void setAlbums(List<AlbumsResponseModel> albums) {
        this.albums = albums;
    }
}
