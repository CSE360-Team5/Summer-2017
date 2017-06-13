package CSE360;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Team6 extends JPanel{


	public Team6()
   {

	//setLayout(new BorderLayout());
	setBackground(Color.RED);



     JButton joel = new JButton("Joel Menja");
	 JButton Manny = new JButton("Manuel Ucles");
     JButton sam = new JButton("Michael warnick");

     add(joel);
     add(Manny);
     add(sam);

    }

}
