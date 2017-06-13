//Authors:  Joel Menja
//          Manuel Ucles
//          Michael Warnick

 
 package CSE360;

 import javax.swing.*;

 public class Team6Ghost extends JLabel implements Runnable {
   

 	public Team6Ghost() { 
 		ImageIcon newImage = new ImageIcon("Team6Images/Pac-Man-Ghost-PNG-Transparent-Image.png"); 
 		setIcon(new ImageIcon(newImage.getImage().getScaledInstance(27, 32, 0))); 
 	}

 	@Override
 	public void run() {
 		int a = 0;
 		int b = 50;
 		int pace = 3;
 		int paceSpeed = pace;
 		int width = 100;
 		int height = 150;
 		
 		while (true) {
 			setBounds(a, b, width, height);
 			if (a < 0) {
 				paceSpeed = pace;
 			}
 			if (a > width) {
 				paceSpeed = -pace;
 			}
 			a += paceSpeed;
 			try {
 				Thread.sleep(50);
 			} catch (InterruptedException e) {
 				System.out.println("Error");
 			}
 		}
 	}
 }
