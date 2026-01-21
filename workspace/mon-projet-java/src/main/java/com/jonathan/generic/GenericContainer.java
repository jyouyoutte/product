package com.jonathan.generic;

import javax.swing.*;
import java.awt.*;

public class GenericContainer <T extends Component>  extends JFrame {
    private T myComponent;

    public T getMyComponent() {
        return myComponent;
    }

    public GenericContainer(Component component)  {
        super("Generic Container");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JPanel contentPane =( JPanel )getContentPane();
        contentPane.add(component, BorderLayout.CENTER);
        this.setContentPane(contentPane);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        GenericContainer<JButton> frame = new GenericContainer<>(new JButton("Click Me"));
        JButton btn = frame.getMyComponent();

        GenericContainer<JTree> frameTree = new GenericContainer<>(new JTree());
        JTree jtree = frameTree.getMyComponent();
    }
}
