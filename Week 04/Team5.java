package CSE360;
 
import java.awt.*;
import java.awt.image.ImageObserver;
 
import javax.swing.*;
 
public class Team5 extends JPanel implements Runnable {
 
	public Thread movingGhost;
	JPanel ghostPanel = new JPanel();
	JLayeredPane layers;
	int dataPanelWidth = 300;
	int dataPanelHeight = 150;
	int x = 5;
	int y = 25;
	
    public Team5() {

    	 JPanel overlapPane = new JPanel();
         createLayers();
    	 
    	 this.createLayers();
    	 add(this);
    	 
    	 //overlapPane.createLayers();
         //frame.add(overlapPane);
    	
    	/*
    	 * WORKS PARTIALLY
    	 */
    	/*JPanel mainPanel = new JPanel();
    	Team5Cover cover = new Team5Cover();
    	Team5City city = new Team5City();
    	cover.add(city);
    	
    	mainPanel.add(cover);
    	
    	add(mainPanel);  */
    	
    	
    	
    	
    	/*
    	Team5 overlapPane = new Team5();
        overlapPane.createLayers();  */
    	
    	/*
    	this.createLayers();
    	
    	
    	add(this); */
    	
    	
    }
   
	
    
	public void createLayers() {
        this.setLayout(new BorderLayout());
        this.setSize(300, 300);
 
        // create layered pane
        layers = new JLayeredPane();
        layers.setSize(300, 300);
 
        // add JPanels to layered pane
       // layers.add(setButtonPanel(), 1);
        layers.add(setGhostPanel(), 0);
        layers.add(setDataPanel(), 2);
 
        this.add(layers);
        
        // start thread for ghost
        movingGhost = new Thread (this);
        movingGhost.start();
    }
	
 
    /*
     * Create panel with city information
     */
    public JPanel setDataPanel() {
        JPanel dataPanel = new JPanel(new GridLayout(1, 2));
 
        setPanel(dataPanel);
        
        Team5City cityInfo = new Team5City();
        dataPanel.add(cityInfo);
        //dataPanel.add(Weather());
        //dataPanel.add(Google());
        dataPanel.setOpaque(true);
        dataPanel.setVisible(true);
        dataPanel.setBounds(0, 25, dataPanelWidth, dataPanelHeight);
       
        
        //MLD
        //cityInfo.PromptForInput();
 
        return dataPanel;
    }
 
    /*
     * Create button for city input
     */
    /*
    public JPanel setButtonPanel() {
        JPanel buttonPanel = new JPanel();
        setPanel(buttonPanel);
        //JButton topLabel = new JButton("Button");
        //topLabel.setFont(new Font("Rockwell", 1, 20));
        //topLabel.setBackground(Color.red);
        //buttonPanel.add(topLabel, BorderLayout.SOUTH);
        buttonPanel.setOpaque(true);
        buttonPanel.setBounds(0, 0, 300, 25);
 
        return buttonPanel;
    }  */
 
    /*
     * Create panel with ghost
     */
    public JPanel setGhostPanel() {
        setPanel(ghostPanel);
        JLabel ghost = new JLabel(new ImageIcon(
                (new ImageIcon("C:/Users/Melissa/Documents/Eclipse/CSE360/src/Images/Ghost.png")).getImage()));
        ghostPanel.add(ghost);
        ghostPanel.setOpaque(false);
        ghostPanel.setBounds(x, y, 100, 100);
 
        return ghostPanel;
    }
 
    /*
     * Set panel size
     */
    public void setPanel(JPanel panel) {
        panel.setSize(200, 300);
        panel.setLayout(new BorderLayout());
    }
 	
    /*
     * Updates ghost's position
     */
 	public void run() {
 		setGhostPanel();
 		ghostPanel.revalidate();
 		ghostPanel.repaint();
 		try {
 			for (int i = 1; i < 100000; i++) {
 				x = x + 10;	// updates ghost's x position
 				y = y + 20; // updates ghost's y position
 				if (x > dataPanelWidth) { // update ghost's location so it stays on panel
 					x = 5;
 				}
 				if (y > dataPanelHeight) { // update ghost's location so it stays on panel
 					y = 10;
 				}
 				ghostPanel.setBounds(x, y, 100, 100);
 				Thread.sleep(100); // making num in parameter seems to make image move faster
 			}
 		} catch (InterruptedException e) {
 		}
 	}
 
 	
 	/*
    public static void main(String[] args) {
 
        JFrame frame = new JFrame("JPanel alignment example");
        frame.setPreferredSize(new Dimension(600, 400));
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
 
 	*/
        //JPanel mainPanel = new JPanel();
    	//Team5Cover cover = new Team5Cover();
    	
    	//mainPanel.add(cover);
        
        /*
         * WORKS
         */
       /* Team5 overlapPane = new Team5();
        overlapPane.createLayers();
        frame.add(overlapPane);
        
    } */

}
 
 
