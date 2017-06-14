package CSE360;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class Team5Ghost extends JPanel implements Runnable {
	
	public Thread movingGhost;
	JPanel ghostPanel = new JPanel();
	int dataPanelWidth = 300;
	int dataPanelHeight = 300;
	int x = 5;
	int y = 5;
	
	//ImageIcon ghostImage = new ImageIcon("src/CSE360/Team5Images/Ghost.png");
	//ImageIcon ghostImage = new ImageIcon("C:/Users/Melissa/Documents/Eclipse/CSE360/src/Team5Images/Ghost.png");
	//JLabel ghostLbl = new JLabel(ghostImage);
	
	public Team5Ghost() {
		
		JPanel Team5GhostPanel = new JPanel();
		Team5GhostPanel.add(setGhostPanel());
		
		movingGhost = new Thread (this);
        movingGhost.start();
        
		add(Team5GhostPanel);
	}
	
	
	/*
	 * Sets JPanel with ghost image
	 */
	public JPanel setGhostPanel() {
        ghostPanel.setSize(200,300);
        
       JLabel ghost = new JLabel(new ImageIcon(
                (new ImageIcon("C:/Users/Melissa/Documents/Eclipse/CSE360/src/Team5Images/Ghost.png")).getImage()));
       ghostPanel.add(ghost);
        
        //ghostPanel.add(ghostLbl);
        ghostPanel.setOpaque(true);
        ghostPanel.setBounds(x, y, 100, 100);
 
        return ghostPanel;
    }
	
    /*
     * Updates ghost's position
     */
 	public void run() {
 		ghostPanel.revalidate();
 		ghostPanel.repaint();
 		try {
 			for (int i = 1; i < 100000; i++) {
 				
 				x = x + 5;	// updates ghost's x position
 				y = y + 5; // updates ghost's y position
 				
 				if (x > dataPanelWidth) { // update ghost's location so it stays on panel
 					x = 5;
 				}
 				if (y > dataPanelHeight) { // update ghost's location so it stays on panel
 					y = 5;
 				}
 				ghostPanel.setBounds(x, y, 100, 100);
 				
 				Thread.sleep(100); // making num in parameter seems to make image move faster
 			}
 		} catch (InterruptedException e) {
 		}
 	}

}
