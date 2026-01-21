package com.jonathan.generic;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.util.Date;

public class GenericMethod {

    private static Component[] components = {
            new JButton("Button 1"),
            new JLabel("Label 1"),
            new JTextField("TextField 1")
    };

    public static <T> T find(Class<T> metadata, int primaryKey) throws Exception {
        Constructor<T> constructor = metadata.getDeclaredConstructor();
        T instance = constructor.newInstance();
        return instance;
    }

    @SuppressWarnings("unchecked")
    public static<T extends Component> T findViewById(int i){
        return (T)components[i];
    }

    public static void main(String[] args) throws Exception{
        Date now = find(Date.class, 1);
        System.out.println("Now: " + now);

        JButton button = find(JButton.class, 2);
        System.out.println(button);

        JLabel jlabel = findViewById(1);
        System.out.println(jlabel);

    }
}
