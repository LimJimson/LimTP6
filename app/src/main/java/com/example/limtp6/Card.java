package com.example.limtp6;

public class Card {
    private int id;
    private int image;
    private boolean faceUp;
    private boolean isMatched;

    public Card(int id, int image) {
        this.id = id;
        this.image = image;
        this.faceUp = false;
        this.isMatched = false;
    }

    public int getId() {
        return id;
    }

    public int getImage() {
        return image;
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }
}

