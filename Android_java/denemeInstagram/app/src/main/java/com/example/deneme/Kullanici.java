package com.example.deneme;

public class Kullanici
{
    String emailAdress;
    int id;
    String passaword;

    public Kullanici(String emailAdress,String passaword)
    {
        this.emailAdress=emailAdress;
        this.passaword=passaword;
    }

    public int getId()
    {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getEmailAdress() {
        return emailAdress;
    }
    public void setEmailAdress(String emailAdress)
    {
        this.emailAdress=emailAdress;
    }

    public String getPassaword()
    {
        return this.passaword;
    }
    public void setPassaword(String passaword)
    {
        this.passaword=passaword;
    }

}
