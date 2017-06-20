package CSE360;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class layer extends JPanel{
	JPanel layer;
	JLabel label;
	
	public layer(){
	  //  layer = new JPanel(new BorderLayout());
		
		JLabel label = new JLabel(new ImageIcon(
		           (new ImageIcon(this.getClass().getResource("/Team5Images/Ghost.png")).getImage())));
		label.setVisible(true);
		//label.setBounds(200, 50, 50, 50);
		add(label);
		
	}
	
	public static void main(String[]args){
		JFrame j = new JFrame();
		layer t = new layer();
		
		t.setVisible(true);
		t.setOpaque(false);
		j.add(t);
		
		j.setVisible(true);
		j.setSize(250, 155);
} 
	}