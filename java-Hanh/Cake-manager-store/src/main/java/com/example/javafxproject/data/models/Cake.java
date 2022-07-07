package com.example.javafxproject.data.models;

public class Cake {
    public int id;
    public String name;
    public int quality;
    public float price;
    public String typecake;
    public String image;

    public Cake(int id, String name, int quality,float price, String typecake, String image) {
        this.id = id;
        this.name = name;
        this.quality = quality;
        this.price = price;
        this.typecake = typecake;
        this.image = image;

    }

    public Cake(String name, int quality, float price, String typecake, String image) {
        this.name = name;
        this.price = price;
        this.quality = quality;
        this.typecake = typecake;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int name) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public float getPrice() {return price;}

    public void setPrice(float price) {
        this.price = price;
    }
    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public String getTypecake() {
        return typecake;
    }

    public void setTypecake(String typecake) {
        this.typecake = typecake;
    }

}

