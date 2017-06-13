package CSE360;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.FlowLayout;

import javax.swing.*;


public class Team5Cover extends JPanel /* implements ActionListener */ {
    JButton button;
    
    //new
    JPanel cover;

    public Team5Cover() {

    	//new
    	cover = new JPanel();
    	//Team5City city = new Team5City();
    	//cover.add(city);
    
      // Add border
      setBorder(BorderFactory.createLineBorder(Color.green, 5));
     
      // Add names
      JLabel label1 = new JLabel("Team 5:");
      JLabel label2 = new JLabel("Melissa Day,");
      JLabel label3 = new JLabel("Austin McCleary,");
      JLabel label4 = new JLabel("Yuxue Zhou,");
      JLabel label5 = new JLabel("Zelin Bao");
   
      // set font & size
      label1.setFont(new Font("Rockwell", 1, 20));
     
   
     
      add(label1);
      add(label2);
      add(label3);
      add(label4);
      add(label5);
    
    
     /*
      button = new JButton(new ImageIcon("/Users/zelinbao/Desktop/setting.jpg"));
      button.setPreferredSize(new Dimension(90,50));
      button.addActionListener(this);
      button.setVisible(true);
      button.setBounds(150, 70, 100, 100);
     
      this.setLayout(new FlowLayout());
      this.setBounds(125, 80, 500, 500);
      this.setVisible(true);
      //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    
      //button.setIcon(new ImageIcon(getClass().getResource("/CSE360project4/src/CSE360/image/setting.jpg")));
   
    add(button); */
  }

   /*
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == button) {
            Team5City T5 = new Team5City();
            
            
        }  
    } */

}