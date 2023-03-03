package com.example.t4pm1p1;

public class Image {
    private long id;
    private String imagePath;
    private String description;

    public Image() {}

    public Image(String imagePath, String description) {
        this.imagePath = imagePath;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

