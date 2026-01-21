package com.jonathan.generic;

public class GenericBox <T>{
    private T item;

    public GenericBox(T item) {
        this.setItem(item);
    }

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        if(item == null){
            throw new IllegalArgumentException("Item cannot be null");
        }
        this.item = item;
    }
}
