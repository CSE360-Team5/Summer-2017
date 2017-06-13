/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE360;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author rsatchel
 */

public class Team4 extends JPanel {

    private JFrame f;
    private JPanel p;
    private JButton b1;
    private JLabel lab;
    
    public Team4(){
        gui();
    }
    
    public void gui(){
  //      f = new JFrame("Frame");
  //      f.setVisible(true);
  //      f.setSize(600,400);
  //      f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    //    p = new JPanel();
          this.setBackground(Color.YELLOW);
        
        b1 = new JButton("TEST");
        
        lab = new JLabel("Sagarika, Ross, Sean");
        
        this.add(b1);
        this.add(lab);
  //      f.add(p,BorderLayout.SOUTH);
        
    }
    
  /*
    public static void main(String[] args) {
        new Team4();
    }
*/    
    
}
