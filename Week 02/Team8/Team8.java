package CSE360;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;

public class Team8 extends JPanel {
	JTextArea text;
	JLabel lable;
	JPanel mainPanel, mapPanel, weatherPanel, labelPanel;
	
	public Team8() {
		
		// Get the saved picture and add it to the label
//		new googlemps_utility("test");
//		lable = new JLabel(new ImageIcon((new ImageIcon("image.jpg"))
//				.getImage()
//				// Scales the image
//				.getScaledInstance(600, 600, java.awt.Image.SCALE_SMOOTH)));
		
//	    // Get the picture without saving and add it to the label
		GoogleMps_UtilityVer2 googleMap = new GoogleMps_UtilityVer2();
		lable = new JLabel(new ImageIcon(googleMap.getPicture()));
		JScrollPane mapScroll = new JScrollPane(lable);
		mapScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mapScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
//        mapScroll.setBounds(0, 0, 30, 30);
        
		
		mapPanel = new JPanel(new BorderLayout());
//		mapPanel.add(new JLabel("Map:", SwingConstants.LEFT), BorderLayout.PAGE_START);
		mapPanel.setPreferredSize(new Dimension(235, 70));
		
//		mapPanel.add(lable);
		mapPanel.add(mapScroll);
		
		// Adding the image to the panel
		
		
		// Get the weather info
		Weather weather = new Weather();
//		System.out.println(weather);
		text = new JTextArea(weather.weatherData.getSentence());
//		System.out.println(weather.weatherData.getSentence());
	    text.setFont(new Font("Serif", Font.PLAIN, 7));
	    text.setEditable(false);
//	    this.add(text, BorderLayout.SOUTH);
	    weatherPanel = new JPanel();
	    weatherPanel.setLayout(new BoxLayout(weatherPanel, BoxLayout.LINE_AXIS));
	    weatherPanel.add(text);
	    
	    // name of the weather channel
	    labelPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
	    labelPanel.add(new JLabel("Weather(Dark sky website): "));
	    
	    
	    mainPanel = new JPanel();
	    mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
	    mainPanel.add(mapPanel);
//	    mainPanel.add(Box.createVerticalStrut(10));
//	    mainPanel.add(labelPanel);
	    mainPanel.add(weatherPanel);
	    this.add(mainPanel);
	    
		this.addComponentListener(new ComponentListener() {
			
			@Override
			public void componentShown(ComponentEvent e) {}
			
			@Override
			public void componentResized(ComponentEvent e) {
//				text.setFont(text.getFont().deriveFont((e.getComponent().getWidth() + e.getComponent().getHeight())/15f));
			}
			
			@Override
			public void componentMoved(ComponentEvent e) {}
			
			@Override
			public void componentHidden(ComponentEvent e) {}
		});
	}
}