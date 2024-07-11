package com.company;

public class Person
{
    private int sno;
    private String id = null;
    private String date = null;

    public Person(int sno, String firstName, String lastName)
    {
        this.sno = sno;
        this.id = firstName;
        this.date = lastName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String firstName) {
        this.date = firstName;
    }

    public String getId() {
        return id;
    }

    public void setId(String lastName) {
        this.id = lastName;
    }

    @Override
    public String toString() {
        return id;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }
}
