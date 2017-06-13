

package CSE360;

import java.text.DecimalFormat;
import java.awt.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.Charset;
import javax.swing.*;
import org.json.JSONException;
import org.json.JSONObject;
/**
 *
 * @author rsatchel
 */
public class Team4 extends JPanel {
    private JTextArea ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8, ta9;
    JPanel mapPanel = new JPanel();
    JPanel weatherPanel = new JPanel();
    
    private Double time = 0.0;
    private Double temp = 0.0;
    private Double nearestStorm = 0.0;
    private Double probPrecip = 0.0;      
    private Double windVelocity = 0.0;
    private Double windDirection = 0.0;
    private Double atmosPressure = 0.0;
	private Double humidity = 0.0;
    private Double vis = 0.0;
    
    
    public Team4()  {    // constructor
		
		try{
    	basePlate();
    	mapPlate();
     	weatherPlate();
        }
		catch(IOException e){
			System.out.println("IOException !!");
		}

		catch(JSONException e){
			System.out.println("JSONException !!");
		}
    }

    private void basePlate(){
        GridLayout basePanel = new GridLayout(1,2);
        setLayout(basePanel);
        this.add(weatherPanel);
        this.add(mapPanel);
                
    }
    
    private JPanel mapPlate(){
        try{
            JPanel mapPanel = new JPanel();
            mapPanel.setSize(250,250);
            
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?" 
                        +"center=33.416952,-111.939817&zoom=9&size=250x250&scale=1&maptype=roadmap";

            String destinationFile = "Team4Image.jpg";
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

        mapPanel.add(new JLabel(new ImageIcon((new ImageIcon("Team4Image.jpg")).getImage().getScaledInstance(125,125,
                    java.awt.Image.SCALE_SMOOTH))));
	    //125,125  630,600
        mapPanel.setVisible(true);
        return mapPanel;
    }
        

    
private JPanel weatherPlate() throws IOException, JSONException{
    //JPanel weatherPanel = new JPanel();
    weatherPanel.setLayout(new GridLayout(6,1));
    String yourKey = "cab82799b5b1e817dbccab51d6d7ec40"; 
    JSONObject json = readJsonFromUrl("https://api.darksky.net/forecast/"
                + yourKey +"/33.421968,-111.936642");
        
    ta1 = new JTextArea(1,20);
    ta2 = new JTextArea(1,20);
    ta3 = new JTextArea(1,20);
    ta4 = new JTextArea(1,20);
    ta5 = new JTextArea(1,20);
	ta6 = new JTextArea(1,20);
        
    ta1.setEditable(false);
    ta2.setEditable(false);
    ta3.setEditable(false);
    ta4.setEditable(false);
    ta5.setEditable(false);
	ta6.setEditable(false);
    
	DecimalFormat oneDigit = new DecimalFormat("#,##0.0");//format to 1 decimal place    
    
    Object timeZone = json.get("timezone");

    temp = json.getJSONObject("currently").getDouble("temperature");
    nearestStorm = json.getJSONObject("currently").getDouble("nearestStormDistance");
    time = json.getJSONObject("currently").getDouble("time");
    probPrecip = json.getJSONObject("currently").getDouble("precipProbability");
    windVelocity = json.getJSONObject("currently").getDouble("windSpeed");
    windDirection = json.getJSONObject("currently").getDouble("windBearing");
    atmosPressure = json.getJSONObject("currently").getDouble("pressure");
    vis = json.getJSONObject("currently").getDouble("visibility");
	humidity = json.getJSONObject("currently").getDouble("humidity") * 100;
	Integer Humidity = humidity.intValue();

    Font labelFont = new Font("Serif", Font.PLAIN, 10);
    Font titleFont = new Font("Serif", Font.BOLD + Font.ITALIC, 12);
    
    this.ta1.setText((String) timeZone);
    this.ta2.setText("Currently: " + json.getJSONObject("currently").getString("summary"));
    this.ta3.setText("Temp: " + String.valueOf(temp) + "°F");
	this.ta4.setText("Humidity: " + String.valueOf(Humidity) + "%");
    this.ta5.setText("Wind: " + String.valueOf(windVelocity) + " mph" + " at " + String.valueOf(windDirection) + "°");
    this.ta6.setText("Pressure: " + String.valueOf(atmosPressure) + " hPa");
        
    ta1.setFont(titleFont);
    ta2.setFont(labelFont);
    ta3.setFont(labelFont);
    ta4.setFont(labelFont);
    ta5.setFont(labelFont);
	ta6.setFont(labelFont);
    
    weatherPanel.add(ta1);
    weatherPanel.add(ta2);
    weatherPanel.add(ta3);
    weatherPanel.add(ta4);
    weatherPanel.add(ta5);
	weatherPanel.add(ta6);

    return weatherPanel;
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







    
