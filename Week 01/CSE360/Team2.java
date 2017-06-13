// CSE360 Team2 Assignment 1
package CSE360;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;

// Block displays a square of varying size and color.
class Block {
        // Size of the block in pixels
	public static int blockSize = 20;
	private int x, y, R, G, B;
        
        // Creates a block with the given RGB values at the given location.
	public Block (int x, int y, int R, int G, int B) {
		this.x = x;
		this.y = y;
		this.R = R;
		this.G = G;
		this.B = B;
	}
        
        // Draws the block at the location with the block's RGB values
	public void draw(Graphics g) {		
		g.setColor(new Color(R, G, B));
		g.fillRect(x*blockSize, y*blockSize, blockSize, blockSize);
	}
	
        // Change the RGB values of the block to set its color.
	public void setColor(int R, int G, int B){
		this.R = R;
		this.G = G;
		this.B = B;
	}

}


public class Team2 extends JPanel implements ActionListener{
	
        // Time between changing block colors
	Timer t = new Timer(100, this);
	
        // Array representing grid of blocks.
	Block[][] blocks = new Block[20][20];
	Random r = new Random();
	
        // Create a Team2 object, populating the Blocks array and starting the timer.
	public Team2() {
		t.start();		
		for (int i = 0; i < blocks.length; i++){
			for (int j = 0; j < blocks[i].length; j++){
				blocks[i][j] = new Block(i, j, r.nextInt(225), r.nextInt(255), r.nextInt(255));
			}
		}
	}
	
        // Update the colors in each block
	public void updateColors() {
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j < blocks[0].length; j++){
				blocks[i][j].setColor(r.nextInt(255), r.nextInt(255), r.nextInt(255));
			}
		}
	}
	
        // Calls to update color and redraw block on update
	public void actionPerformed (ActionEvent arg0) {
		updateColors();
		repaint();	
	}
	
        // Redraw each block with new the new color
	public void paint(Graphics g){
		for (int i = 0; i < blocks.length; i++) {
			for (int j = 0; j<blocks[0].length; j++){
				blocks[i][j].draw(g);
			}
		}
	}

}
