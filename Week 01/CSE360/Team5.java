package CSE360;


import java.awt.*;
import javax.swing.*;

public class Team5 extends JPanel {

  public Team5() {

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
  }
}
