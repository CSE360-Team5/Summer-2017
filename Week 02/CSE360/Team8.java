package CSE360;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.swing.*;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

import org.json.*;



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
    ///////////////////////////////////////////////////////////
	public class GoogleMps_UtilityVer2 {

	    private String api_url = "https://maps.googleapis.com/maps/api/staticmap?";
	    private String api_key = "AIzaSyCPBkB4FfvXzIYRgbRBmAMxqXLhnaGJVXU";
	    private URL url1;
	    private byte[] picture = new byte[68108];


	    public GoogleMps_UtilityVer2() {

	        String center = "center=33.418802,-111.933154&";
	        String zoom = "zoom=11&";
	        String size = "size=612x612&";
	        String scale = "scale=1&";
	        String mapType = "maptype=roadmap&";

	        api_url = api_url + center + zoom + size + scale + mapType;
	        api_url = api_url + "key=" + api_key;

	        try {
	            url1 = new URL(api_url);
	        } catch (java.net.MalformedURLException e) {
	            //?
	        }

	        try {
	            //int count = 0;
	            byte[] read;

	            InputStream inStream;
	            inStream = url1.openStream();

	            /*
	            while(inStream.read() != -1){
	                count++;
	            }
	            System.out.print(count);
	            */

	            for (int i = 0; i < 68108; i++){
	                read = ByteBuffer.allocate(4).putInt(inStream.read()).array();
	                picture[i] = read[3];
	            }

	            inStream.close();
	        } catch (java.io.IOException e){
	            System.exit(1);
	        }
	    }

	    public byte[] getPicture() {
	        return picture;
	    }
	}
    ///////////////////////////////////////////////////////////
	public class Weather extends JPanel{
	    
	  private String summary;
	  private double windSpeed;
	  private double temperature;
	  private double humidity;
	  WeatherData weatherData;
	  
	  Weather()
	  {
	    this._run();
	  }
	  private void _run()
	  {
	      try 
	      {
	          String yourKey = "fdd8a0537b1af5efefd850be6d1e3488"; 
	          JSONObject json = readJsonFromUrl("https://api.darksky.net/forecast/"
	                  +yourKey+"/33.418802,-111.933154");
	          summary = json.getJSONObject("currently").getString("summary");
	          windSpeed = json.getJSONObject("currently").getDouble("windSpeed");
	          temperature = json.getJSONObject("currently").getDouble("temperature");
	          humidity = json.getJSONObject("currently").getDouble("humidity");
	          
	      } 
	      catch (IOException | JSONException ex) 
	      {
	          Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
	      }
	      weatherData = new WeatherData(summary, windSpeed, temperature, humidity); 
	      //      _add();
	  }
	  private String readAll(Reader rd) throws IOException
	  {
	    StringBuilder sb = new StringBuilder();
	    int cp;
	    while ((cp = rd.read()) != -1){
	      sb.append((char) cp);
	    }
	    return sb.toString();
	  }

	  private JSONObject readJsonFromUrl(String url) throws IOException, JSONException 
	  {
	    try (InputStream is = new URL(url).openStream()){
	      BufferedReader rd = new BufferedReader(
	              new InputStreamReader(is, Charset.forName("UTF-8")));
	      String jsonText = readAll(rd);
	      JSONObject json = new JSONObject(jsonText);
	      return json;
	    }
	  }
	 
	  private void _add()
	  {
	      JTextArea disp = new JTextArea( "Weather: " + summary + "\n" +
	                                      "Temperature(f): " + temperature + "\n" +
	                              "Wind Speed(m): " + windSpeed + "\n" +
	                              "Humidity: " + humidity + "\n" );
	      
	      disp.setLayout(new FlowLayout(FlowLayout.CENTER, 500, 150));
	      disp.setFont(new Font("Serif", Font.BOLD, 15));
	      disp.setEditable(false);
	      this.add(disp);
	  }
	}
    //////////////////////////////////////////////////////////
	public class WeatherData {
		private String summary;
		private Double windSpeed;
		private Double temperature;
		private Double humidity;
//		private Double e;
		
		public WeatherData(String _a, Double _b, Double _c, Double _d){
			this.setSummary(_a);
			this.setWindSpeed(_b);
			this.setTemperature(_c);
			this.setHumidity(_d);
		}
		
		public String getSummary() {
			return summary;
		}
		public void setSummary(String a) {
			this.summary = a;
		}
		public Double getWindSpeed() {
			return windSpeed;
		}
		public void setWindSpeed(Double b) {
			this.windSpeed = b;
		}
		public Double getTemperature() {
			return temperature;
		}
		public void setTemperature(Double c) {
			this.temperature = c;
		}
		public Double getHumidity() {
			return humidity;
		}
		public void setHumidity(Double d) {
			this.humidity = d;
		}
//		public Double getE() {
//			return e;
//		}
//		public void setE(Double e) {
//			this.e = e;
//		}
		public String getSentence() {
			String S = "Weather: " + this.getSummary() + "\n" +
	  			  "Temperature(f): " + this.getTemperature() + "\n" +
	  			  "Wind Speed(m): " + this.getWindSpeed() + "\n" +
	  			  "Humidity: " + this.getHumidity() + "\n" ;
			return S;
		}
		
	}

}