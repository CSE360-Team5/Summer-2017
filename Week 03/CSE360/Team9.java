package CSE360;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import org.json.JSONException;
import org.json.JSONObject;


/**
 *
 * @author ross satchell
 */
 
public class Team9 extends JPanel {
    private JTextArea ta1, ta2, ta3, ta4, ta5, ta6, ta7; // text areas to hold weather data
    
    JPanel mapPanel = new JPanel();             // panel to hold the map
    JPanel weatherPanel = new JPanel();         // panel to hold the weather data
    JPanel buttonPanel = new JPanel();          // panel to hold the button
    JPanel basePanel = new JPanel();            // base panel to hold both weather and map
    
    JPanel comboPanel = new JPanel();           // comboPanel holds a comboBox for selecting cities
    JPanel comboSubPanel = new JPanel();        // this is used to make the comboBox smaller and look nicer 
    JPanel comboSelectionPanel = new JPanel();
    JPanel comboImagePanel = new JPanel();


   
    JLabel selectCity = new JLabel("Select City");  // label to go beside the combo panel "Select City"
    JButton button1 = new JButton("Change City");   // button to reset and change the city selected

    
    private String time = "";                      // time of measurement
    private Double tempF = 0.0; 
    private Double tempC = 0.0; 
    private Double nearestStorm = 0.0;
    private Double probPrecip = 0.0;                // variables used in extracting 
    private Double windVelocityKph = 0.0;           // weather data from JSON
    private Double windDirectionDegrees = 0.0;
    private String windDir = "";
    private String atmosPressure;
    private String humidity = "";
    private String conditions = "";
    private Double vis = 0.0;
    private int uv_rad = 0;
    private String url_icon = "";
    private String icon_descrip = "";
    
    
    
    private Double _lat = 0.0;
    private Double _long = 0.0;
    private String lat_long = "";                   // used to pass coordinates to map and weather panels
    private String city = "";                       // used to pass name of city to weather panel

    private String cities[] = {"Phoenix", "Bisbee", "Los Angeles" , "Boise", "Miami", "Houston",     // list of cities
                            "New York", "Chicago", "Cleveland", "Nashville", "Seattle"};    // to select from
    private JComboBox<String> cityBox = new JComboBox<>(cities);    // comboBox to hold city names


    
    
    public Team9()  {    // constructor	
		
	cityBox.addActionListener(cbActionListener);	    // add comboBox ActionListener
	comboSubPanelPlate();                               // start with comboPanel of cities
	basePlateComboBox();                                //      which is placed on comboSubPanel
	comboPlate();  	                                    // add the cityBox to the comboPanel
      		
    }



ActionListener cbActionListener = new ActionListener(){     // ActionListener for the comboBox
      @Override
      public void actionPerformed(ActionEvent e){
        String s =  (String) cityBox.getSelectedItem();     // get city name from comboBox

        switch(s) {                                                    
          case "Phoenix":                                   // depending on city selected, set the coordinates
            _lat = 33.448376 - 0.1;
            _long = -112.074036;
            city = "Phoenix AZ";
            break;
          case "Bisbee":
            _lat = 31.448155;
            _long = -109.928408;
            city = "Bisbee AZ";     
            break;       
          case "Los Angeles":
            _lat = 34.052235 - 0.1;
            _long = -118.243683;
            city = "Los Angeles CA";
            break;
          case "Boise":
            _lat = 43.618881 - 0.1;
            _long = -116.215019;
            city = "Boise ID";
            break;
          case "Miami":
            _lat = 25.774271;// - 0.1;
            _long = -80.193661;
            city = "Miami FL";
            break;
          case "Houston":
            _lat = 29.780472 - 0.1;
            _long = -95.386342;
            city = "Houston TX";
            break;
          case "New York":
            _lat = 40.664274;// - 0.1;
            _long = -74.005970;
            city = "New York NY";
            break;
          case "Chicago":
            _lat = 41.837551;// - 0.1;
            _long = -87.681844;
            city = "Chicago IL";
            break;
          case "Cleveland":
            _lat = 41.478138 - 0.1;
            _long = -81.679486;
            city = "Cleveland OH";
            break;
          case "Nashville":
            _lat = 36.165890;// - 0.1;
            _long = 86.784443;
            city = "Nashville";
            break;
          case "Seattle":
            _lat = 47.620499 - 0.1;
            _long = -122.350876;
            city = "Seattle WA";
            break;
        }
        lat_long = String.valueOf(_lat) + "," + String.valueOf(_long);
        //System.out.println("Lat_Long = " + lat_long);         // used for testing
        
        mapPlate(lat_long);                                     // get map of the chosen coordinates
        weatherPlate(lat_long, city);                           // get weather data for the coordinates and include city name
        basePlateData();                                        // add panels for the weather and map display
      }
    };

ActionListener buttonListener = new ActionListener(){
	@Override
	public void actionPerformed(ActionEvent e){
		repaint();
		basePlateComboBox();
	}
};


	private JPanel basePlateComboBox(){
        removeAll();
	    repaint();
	    revalidate();

		setLayout(new GridLayout(1,1));
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.add(comboSubPanel);
		return this;
	}


	private JPanel comboSubPanelPlate(){
		comboSubPanel.removeAll();
		comboSubPanel.revalidate();
		comboSubPanel.repaint();
        setLayout(new GridLayout(1,1));
		comboSubPanel.add(comboPanel);
		return comboSubPanel;	
	}
		

	
	private JPanel comboPlate(){
		//comboPanel.setLayout(new GridLayout(1,1));
		//comboPanel.add(selectCity);
		//comboPanel.add(cityBox);
		//return comboPanel;
		comboImagePanel.setLayout(new FlowLayout());
        //comboSelectionPanel.setLayout(new GridLayout(1,2));
        comboSelectionPanel.setLayout(new FlowLayout());
        comboPanel.setLayout(new BorderLayout());
        comboImagePanel.add(new JLabel(new ImageIcon((new ImageIcon("CSE360/wunderground_logo.jpg")).getImage().
                getScaledInstance(200, 60, java.awt.Image.SCALE_SMOOTH))));
        comboPanel.add(comboImagePanel,BorderLayout.NORTH);
        comboSelectionPanel.add(selectCity);
        comboSelectionPanel.add(cityBox);
        comboPanel.add(comboSelectionPanel, BorderLayout.SOUTH);
        return comboPanel;
	}

        
	private JPanel basePlateData(){
		this.removeAll();
		this.revalidate();
		
        setLayout(new BorderLayout());
        
		basePanel.setLayout(new GridLayout(1,2));

		this.repaint();

		basePanel.add(weatherPanel);
		basePanel.add(mapPanel);
		basePanel.setBorder(BorderFactory.createRaisedBevelBorder());
		
		buttonPanel.add(button1);

		this.add(basePanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);

		button1.addActionListener(buttonListener);
		return basePanel;
		
	}

    private void basePlate(){

        this.add(weatherPanel);
        this.add(mapPanel);                
    }
    
    
    private JPanel mapPlate(String coords){
        try{
            String latlong = coords;
            JPanel mapPanel = new JPanel();
            
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" 
                + latlong + "&zoom=9&size=500x500&scale=1&maptype=roadmap";

            String destinationFile = "mapImage.jpg";
            String str = destinationFile;
            URL url = new URL(imageUrl);
            OutputStream os;
                
            try (InputStream is = url.openStream()) {
                os = new FileOutputStream(destinationFile);
                byte[] b = new byte[2048];
                int length;
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
            }
            os.close();
        } 
        catch (IOException e) {
            System.exit(1);
        }
	mapPanel.removeAll();
	mapPanel.repaint();
        mapPanel.add(new JLabel(new ImageIcon((new ImageIcon("mapImage.jpg")).getImage().getScaledInstance(125,125,
                    java.awt.Image.SCALE_SMOOTH))));
        mapPanel.setVisible(true);
        return mapPanel;
    }
        



    
private JPanel weatherPlate(String coords, String location) {
    try{
        String latlong = coords;
        String cityName = location;        
        
        weatherPanel.setLayout(new GridLayout(7,1));
	    
       
        String yourKey = "2ebb44a45e67c7f1";
        JSONObject json = readJsonFromUrl("http://api.wunderground.com/api/" + yourKey + 
            "/conditions/q/" + latlong + ".json");        
            
        ta1 = new JTextArea(1,20);
        ta2 = new JTextArea(1,20);
        ta3 = new JTextArea(1,20);
        ta4 = new JTextArea(1,20);
        ta5 = new JTextArea(1,20);
	    ta6 = new JTextArea(1,20);
	    ta7 = new JTextArea(1,20);
            
        ta1.setEditable(false);
        ta2.setEditable(false);
        ta3.setEditable(false);
        ta4.setEditable(false);
        ta5.setEditable(false);
	    ta6.setEditable(false);
	    ta7.setEditable(false);
        
	    DecimalFormat oneDigit = new DecimalFormat("#,##0.0");  //format to 1 decimal place    
        
        Object curr_obs = json.get("current_observation");      // JSON object from which all our weather data comes from
        
        time = json.getJSONObject("current_observation").getString("observation_time");
        tempF = json.getJSONObject("current_observation").getDouble("temp_f");
        tempC = json.getJSONObject("current_observation").getDouble("temp_c");
        humidity = json.getJSONObject("current_observation").getString("relative_humidity");
        uv_rad = json.getJSONObject("current_observation").getInt("UV");
        url_icon = json.getJSONObject("current_observation").getString("icon_url");
        icon_descrip = json.getJSONObject("current_observation").getString("icon");
        windVelocityKph = json.getJSONObject("current_observation").getDouble("wind_kph");
        windDir = json.getJSONObject("current_observation").getString("wind_dir");
        atmosPressure = json.getJSONObject("current_observation").getString("pressure_mb");
        //System.out.println(atmosPressure);
        
        
        String iconUrl;
        iconUrl = "http://icons.wxug.com/i/c/k/" + icon_descrip + ".gif";
        String clearIconFile = "icon.gif";
        String str = clearIconFile;
        URL clearIconUrl = new URL(iconUrl);
        OutputStream os;
        
        try (InputStream is = clearIconUrl.openStream()) {
            os = new FileOutputStream(clearIconFile);
            byte[] b = new byte[2048];
            int length;
            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }
        }
        os.close();

        Font labelFont = new Font("Serif", Font.PLAIN, 9);
        Font titleFont = new Font("Serif", Font.BOLD + Font.ITALIC, 8);
        
        this.ta1.setText(cityName);
        this.ta2.setText("Temp: " + String.valueOf(tempF) + "°F" + " / " + String.valueOf(tempC) + "°C");
        this.ta3.setText("Humidity: " + humidity);
        this.ta4.setText("UV Index: " + String.valueOf(uv_rad));
        this.ta5.setText("Wind: " + String.valueOf(windVelocityKph) + "kph, " + windDir);
        this.ta6.setText("Pressure: " + atmosPressure + " mb");
            
        ta1.setFont(titleFont);
        ta2.setFont(labelFont);
        ta3.setFont(labelFont);             // set fonts for city name 
        ta4.setFont(labelFont);             // and weather conditions
        ta5.setFont(labelFont);
	    ta6.setFont(labelFont);
        
        weatherPanel.removeAll();           // clear contents of last repaint
        
       
        
        weatherPanel.add(ta1);              // add city name
        
        weatherPanel.add(new JLabel(new ImageIcon((new ImageIcon("icon.gif")).getImage().   // add current conditions
                    getScaledInstance(12, 12, java.awt.Image.SCALE_SMOOTH))));              // icon
                    
        weatherPanel.add(ta2);              // add temp
        weatherPanel.add(ta3);              // add humidity
        weatherPanel.add(ta4);              // add UV Index
        weatherPanel.add(ta5);              // add Wind speed and direction
	    weatherPanel.add(ta6);              // add atmospheric pressure
	    
	    weatherPanel.repaint();             // repaint the panel

        return weatherPanel;
    }
	catch(IOException e){
	    System.out.println("IOException !!");
		return null;
	}
	catch(JSONException e){
	    System.out.println("JSONException !!");
		return null;
	}
}    
    
    

private static String readAll(Reader rd) throws IOException {
    StringBuilder sb = new StringBuilder();
    int cp;
    while ((cp = rd.read()) != -1) {
      sb.append((char) cp);
    }
    return sb.toString();
}



  public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    InputStream is = new URL(url).openStream();
    try {
      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
      String jsonText = readAll(rd);
      JSONObject json = new JSONObject(jsonText);
      return json;
    } finally {
        
      is.close();
    }
  }
    
}


    
