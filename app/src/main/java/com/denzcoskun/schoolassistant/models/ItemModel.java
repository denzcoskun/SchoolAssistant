package com.denzcoskun.schoolassistant.models;

/**
 * Created by Denx on 5.06.2018.
 */
public class ItemModel {
    private int itemName;
    private int itemImage;

    public ItemModel(int itemName, int itemImage) {
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    public int getItemName() {
        return itemName;
    }

    public void setItemName(int itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}
