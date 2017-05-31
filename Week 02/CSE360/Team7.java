package CSE360;

import java.io.FileReader;
import java.lang.String;
import java.io.*;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.*;

import java.io.FileReader;
import java.lang.Object;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Date;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import java.awt.*;
import java.text.SimpleDateFormat;
import javax.swing.*;
import java.util.Date;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JFrame;
import org.json.JSONException;

class Team7 extends JPanel
{
    double latitude = 33.4255, longitude = 111.9400;
    Team7()
    {
        GridLayout grid = new GridLayout(2, 1);
        setLayout(grid);
        setVisible(true);
        
        WeatherPanel weather = new WeatherPanel(latitude, longitude);
        googleMap map = new googleMap(latitude, longitude);
        add(map);
        add(weather);
    }
}

class WeatherInfo {
    private Timestamp curTime;
    private Date curDate;
    private JSONObject darksky;
    private double latitude;
    private double longitude;
    
    public WeatherInfo(double latitude, double longitude){
        this.latitude = latitude; this.longitude = longitude;
        curDate = new Date();
        curTime = new Timestamp(curDate.getTime());
        updateDarkSKYJSONObject();
    }
    public void UpdateGeoLocation(double latitude, double longitude){
        this.latitude = latitude; this.longitude = longitude;
        curDate = new Date();
        curTime = new Timestamp(curDate.getTime());
        updateDarkSKYJSONObject();
    }
    public String getLongitude() { 
        return darksky.getString("longitude");
    }
    public String getLatitude() { 
        return darksky.getString("latitude");
    }
    public String getTimezone() { 
        return darksky.getString("timezone");
    }
    private boolean isValid_TimeType(String timeType){ 
        if (    timeType.matches("currently")||
                timeType.matches("minutely")||
                timeType.matches("hourly")||
                timeType.matches("daily")
            ) { return true; }
        else { return false; }
    }
    private boolean isValid_WeatherInfoKey(String wkey){ 
        if(     wkey.matches("time")|| 
                wkey.matches("summary")|| 
                wkey.matches("icon")|| 
                wkey.matches("nearestStormDistance")|| 
                wkey.matches("precipIntensity")|| 
                wkey.matches("precipIntensityError")|| 
                wkey.matches("precipProbability")||
                wkey.matches("precipType")||
                wkey.matches("temperature")||
                wkey.matches("apparentTemperature")||
                wkey.matches("dewPoint")||
                wkey.matches("humidity")||
                wkey.matches("windSpeed")||
                wkey.matches("windBearing")||
                wkey.matches("visibility")||
                wkey.matches("cloudCover")||
                wkey.matches("pressure")||
                wkey.matches("ozone")
            ) { return true; }
        else { return false; }
    }
    private boolean isWeatherInfoKey_String(String wkey){ 
        if(
                wkey.matches("summary")|| 
                wkey.matches("icon")
            ) { return true; }
        else { return false; }
    }
    public String getWeatherFieldString(String timeType, String wkey){
        if ( !isValid_TimeType(timeType) ) { return "ERROR: Invalid timeType (valid: currently, minutely, hourly, daily)"; }
        else if ( !isValid_WeatherInfoKey(wkey)) {  return "ERROR: Invalid weather key";  }
        else { 
            if(isWeatherInfoKey_String(wkey)) { 
              return darksky.getJSONObject(timeType).getString(wkey); 
            }
            else { 
                return String.valueOf(darksky.getJSONObject(timeType).getDouble(wkey));
            }
        }
    }
    
    private JSONObject getLatestWeatherJSON() {
        curDate = new Date();
        curTime = new Timestamp(curDate.getTime());
        updateDarkSKYJSONObject();
        return darksky;
    }
    private void updateDarkSKYJSONObject() { 
        String ourAPIKey = "fc32de3a545df155ae6e26a367e4259f";
        String darkSKYURL_ForecastRequest = "https://api.darksky.net/forecast/"+ourAPIKey+"/"+String.valueOf(this.latitude)+","+String.valueOf(this.longitude);
        System.out.print(darkSKYURL_ForecastRequest);
        try { darksky = readJSONFromURL(darkSKYURL_ForecastRequest); }
        catch(IOException e) {} // ignoring exceptions for now
    }
    // reusing the provided StringBuilder method provided by Rao 
    //[originally in CSE360 L06 - recitation 02.pdf, Main.java example]
    private static String readAll(Reader rd) throws IOException{
        StringBuilder sb = new StringBuilder();
        int cp; 
        while((cp=rd.read())!=-1) { // while reader is not at end of buffer
            sb.append((char)cp); // append the current character to stringbuilder
            System.out.print((char)cp);
        }
        return sb.toString(); //need actual string within buffer, returning that
    }
    //based on the readJsonFromURL method provided by Rao
    //[originally in CSE360 L06 - recitation 02.pdf, Main.java example]
    private static JSONObject readJSONFromURL(String url) throws IOException, JSONException { 
        try (InputStream is = new URL(url).openStream() // opens URL stream
        ) {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is,Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            is.close();
            return json;
        } 
    } 


    private JSONObject getCurrently() {
        return darksky.getJSONObject("currently");
    }
    private JSONObject getMinutely() {
        return darksky.getJSONObject("minutely");
    }
    private JSONObject getHourly() {
        return darksky.getJSONObject("hourly");
    }
    private JSONObject getDaily() {
        return darksky.getJSONObject("daily");
    }
    private JSONObject getAlerts() {
        return darksky.getJSONObject("alerts");
    }
    private JSONObject getFlags() {
        return darksky.getJSONObject("flags");
    }

}

class WeatherPanel extends JPanel {

    private WeatherInfo geoLocation;
    private ImageIcon weatherIcon;
    private JLabel weatherLabel;


    /**
     * Creates new form WeatherPanel
     */
    public WeatherPanel(double latitude, double longitude)  {
        geoLocation = new WeatherInfo(latitude,longitude);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents()  {
        TextHumidity = new javax.swing.JTextField();
        TextTemperature = new javax.swing.JTextField();
        TextPrecipitationProbability = new javax.swing.JTextField();
        TextCloudCover = new javax.swing.JTextField();
        weatherIcon = new ImageIcon("src\\cse360\\images\\"+geoLocation.getWeatherFieldString("currently","icon")+".png");
        
        weatherLabel = new JLabel("", weatherIcon, JLabel.CENTER);
        weatherLabel.setIcon(weatherIcon);
        setLayout(new java.awt.BorderLayout());

        add(weatherLabel,java.awt.BorderLayout.CENTER);
        TextHumidity.setText(geoLocation.getWeatherFieldString("currently", "humidity")+" [Humidity]");
        add(TextHumidity, java.awt.BorderLayout.PAGE_START);

        TextTemperature.setText(geoLocation.getWeatherFieldString("currently", "temperature")+"\u00b0"+" F [Temperature]");
        add(TextTemperature, java.awt.BorderLayout.PAGE_END);

        TextPrecipitationProbability.setText(geoLocation.getWeatherFieldString("currently", "precipProbability")+" [Probability of Precipitation]");
        add(TextPrecipitationProbability, java.awt.BorderLayout.LINE_END);

        TextCloudCover.setText(geoLocation.getWeatherFieldString("currently", "cloudCover")+" [CloudCover]");
        add(TextCloudCover, java.awt.BorderLayout.LINE_START);
    }

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }


    // Variables declaration - do not modify                     
    private javax.swing.JPanel IconImage;
    private javax.swing.JTextField TextCloudCover;
    private javax.swing.JTextField TextPrecipitationProbability;
    private javax.swing.JTextField TextTemperature;
    private javax.swing.JTextField TextHumidity;
    // End of variables declaration                   
    
}

class googleMap extends JPanel{
    double latitude,longitude;
    //constructor
    public googleMap(double latitude, double longitude)     {
    	this.latitude = latitude;
    	this.longitude = longitude;
    	
   
    	int zoom = 11;
    	
        try {
            String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?" + "center=" + latitude + "," + longitude + 
            		"&zoom=" + zoom + "&size=640x640&maptype=road";
            String outputImageFile = "mycity.jpg";
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(outputImageFile);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        } catch (IOException e) {
            System.exit(1);
        }
        //add(new JLabel(new ImageIcon((new ImageIcon("mycity.jpg")).getImage().getScaledInstance(250, 250,
                //java.awt.Image.SCALE_SMOOTH))));
        //setVisible(true);
        add(new JLabel(new ImageIcon((new ImageIcon("mycity.jpg")).getImage().getScaledInstance(200, 200,
                java.awt.Image.SCALE_SMOOTH))));
        setVisible(true);
}
}
