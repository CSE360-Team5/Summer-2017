package CSE360;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

public class Team8 extends JPanel {

	String[] names = {"Michael", "Yaqoub", "Amit"};
	JTextArea text;
	public Team8() {
		this.setBackground(Color.CYAN);
		text = new JTextArea(names[0] + "\n" + names[1] + "\n" + names[2]);
		text.setFont(text.getFont().deriveFont((this.getWidth()+this.getHeight())/15f));
		text.setBackground(Color.CYAN);
		text.setEditable(false);
		this.add(text);
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
				text.setFont(text.getFont().deriveFont((e.getComponent().getWidth() + e.getComponent().getHeight())/15f));
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
	}
}
