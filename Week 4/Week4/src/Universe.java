/**
 * Main class
 * 
 * @author Javier Gonzalez-Sabchez
 * @author Manohara Rao Penumala
 */

import java.awt.GridLayout;
import javax.swing.JFrame;

public class Universe extends JFrame {

    public Universe () {
     GridLayout grid = new GridLayout (4,2);   
     setLayout(grid);
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
     this.add(new Team3());
    }

    public static void main(String[] args) {
        Universe u = new Universe();
        u.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        u.setSize(500, 500);
        u.setVisible(true);
    }
    
}
