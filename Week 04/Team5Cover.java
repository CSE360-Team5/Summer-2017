package CSE360;

import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Team5Cover extends JPanel implements ActionListener {
	JButton button;
	JPanel main;

	public Team5Cover() {
		main = new JPanel();
		// Add border
		main.setBorder(BorderFactory.createLineBorder(Color.green, 5));

		// Add names
		JLabel label1 = new JLabel("Team 5:");
		JLabel label2 = new JLabel("Melissa Day,");
		JLabel label3 = new JLabel("Austin McCleary,");
		JLabel label4 = new JLabel("Yuxue Zhou,");
		JLabel label5 = new JLabel("Zelin Bao");

		// set font & size
		label1.setFont(new Font("Rockwell", 1, 20));

		main.add(label1);
		main.add(label2);
		main.add(label3);
		main.add(label4);
		main.add(label5);

		button = new JButton(new ImageIcon("/Users/zelinbao/Desktop/setting.jpg"));
		button.setPreferredSize(new Dimension(90, 50));
		button.addActionListener(this);
		button.setVisible(true);
		//button.setBounds(150, 70, 100, 100);

		//main.setLayout(new FlowLayout());
		//main.setBounds(125, 80, 500, 500);
		//main.setVisible(true);
		// this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		main.add(button);
		
		add(main);
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == button) {
			
			
			Team5City T5 = new Team5City();

			main.removeAll();
			main.setBorder(BorderFactory.createEmptyBorder());
			
			
			// JFrame main = new JFrame();
			main.add(T5);
			
			revalidate();
			repaint();
			//main.setVisible(true);
			//main.setBounds(300, 300, 600, 500);
			
		}
	}

}
