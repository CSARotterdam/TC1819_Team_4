package com.example.mainmenu;

public class userProfile {
    public String userName;
    public String userSurname;
    public String userNickName;
    public String userBday;
    public String userEmail;
    public String userID;
    public String userPhone;
    public String userClass;

    public userProfile(){
    }

    public userProfile(String userName, String userSurname, String userNickName, String userBday, String userEmail, String userID, String userPhone, String userClass) {
        this.userName = userName;
        this.userSurname = userSurname;
        this.userNickName = userNickName;
        this.userBday = userBday;
        this.userEmail = userEmail;
        this.userID = userID;
        this.userPhone = userPhone;
        this.userClass = userClass;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getUserNickName() {
        return userNickName;
    }

    public void setUserNickName(String userNickName) {
        this.userNickName = userNickName;
    }

    public String getUserBday() {
        return userBday;
    }

    public void setUserBday(String userBday) {
        this.userBday = userBday;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass;
    }
}



