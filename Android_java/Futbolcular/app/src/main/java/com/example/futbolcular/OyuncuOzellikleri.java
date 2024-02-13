package com.example.futbolcular;

import java.io.Serializable;

public class OyuncuOzellikleri implements Serializable
{
    String isim;
    String ulke;
    String mevki;
    int  potansiyel;
    int image;

    public OyuncuOzellikleri(String isim, String ulke, String mevki, int potansiyel, int image)
    {
        this.image=image;
        this.isim=isim;
        this.potansiyel=potansiyel;
        this.ulke=ulke;
        this.mevki=mevki;
    }


}
