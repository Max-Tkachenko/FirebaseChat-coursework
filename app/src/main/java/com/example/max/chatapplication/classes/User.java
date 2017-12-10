package com.example.max.chatapplication.classes;

import java.util.List;

public class User {

    public static String name;
    public static String surname;
    public static String years;
    public static String country;
    public static String city;

    public List<Notification> notes;

    public User(String name, String surname, String years, String country, String city) {
        this.name = name;
        this.surname = surname;
        this.years = years;
        this.country = country;
        this.city = city;
    }

    public User(){

    }

    public void addNote(Notification note){
        this.notes.add(notes.size()-1, note);
    }

    public void removeNote(int index){
        this.notes.remove(index);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
