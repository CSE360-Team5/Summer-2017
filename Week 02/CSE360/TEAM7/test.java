package team7;

import java.awt.Container;
import java.awt.GridLayout;
import java.io.IOException;

import javax.swing.JFrame;

public class test extends JFrame {
    public test()  {
     GridLayout grid = new GridLayout (4,2);   
     setLayout(grid);
     googleMap p;
	 p = new googleMap(40.7127837,-74.00594130000002);
     add(p);
	}

    public static void main(String[] args) {
        test u = new test();
        u.setTitle("Universe Frame");
        u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        u.setSize(500, 500);
        u.setVisible(true);
    }
    
}