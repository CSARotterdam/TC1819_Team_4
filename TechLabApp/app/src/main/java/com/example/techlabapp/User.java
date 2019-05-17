package com.example.techlabapp;

public class User {
    int studNum;
    String name, email, pass, rank;

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail(){
        return this.email;
    }

    public void setStudNum(int studNum) {
        this.studNum = studNum;
    }

    public int getStudNum(){
        return this.studNum;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass(){
        return this.pass;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getRank(){
        return this.rank;
    }


}
