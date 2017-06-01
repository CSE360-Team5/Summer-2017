package CSE360;

import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JPanel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

import org.json.*;

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

