package com.example.chatapp;

public class Users {
    String profilepic, mail, userName, password, userId, lastMessage, status;
    private String role;     // "admin" | "user"
    private Boolean banned;

    public Users() {}

    public Users(String userId, String userName, String mail, String password, String profilepic, String status) {
        this.userId = userId;
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.profilepic = profilepic;
        this.status = status;
        this.role = "user"; // Mặc định là user
        this.banned = false; // Mặc định không bị ban
    }

    // Các getter và setter
    public String getUid() { return userId; }
    public void setUid(String uid) { this.userId = uid; }

    public String getName() { return userName; }
    public void setName(String name) { this.userName = name; }

    public String getEmail() { return mail; }
    public void setEmail(String email) { this.mail = email; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Boolean getBanned() { return banned; }
    public void setBanned(Boolean banned) { this.banned = banned; }

    public String getProfilepic() { return profilepic; }
    public void setProfilepic(String profilepic) { this.profilepic = profilepic; }

    public String getMail() { return mail; }
    public void setMail(String mail) { this.mail = mail; }

    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getLastMessage() { return lastMessage; }
    public void setLastMessage(String lastMessage) { this.lastMessage = lastMessage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}