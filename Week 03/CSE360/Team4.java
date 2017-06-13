/*Authors:
*Sean Casaus
*Sagarika Pannase
*/

package CSE360;

import java.text.DecimalFormat;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.Charset;
import javax.swing.*;
import org.json.JSONException;
import org.json.JSONObject;

public class Team4 extends JPanel {
    private JTextArea ta1, ta2, ta3, ta4, ta5, ta6, ta7, ta8, ta9;
    JPanel mapPanel = new JPanel();
    JPanel weatherPanel = new JPanel();
    JPanel cityPanel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel centerPanel = new JPanel();

    private Double time = 0.0;
    private Double temp = 0.0;
    private Double nearestStorm = 0.0;
    private Double probPrecip = 0.0;
    private Double windVelocity = 0.0;
    private Double windDirection = 0.0;
    private Double atmosPressure = 0.0;
    private Double humidity = 0.0;
    private Double vis = 0.0;
    private String[] choices = {"Phoenix", "Los Angeles", "San Diego", "Nashville", "Tampa", "Austin", "Portland", "Seattle", "San Jose", "Anchorage"};
    private String[] coordinates = {"33.421968,-111.936642", "34.0544,-118.2439", "32.7174,-117.1628", "36.1622,-86.7744", "27.9478,-82.4584", "30.2711,-97.7437", "45.5202,-122.6742", "47.6038,-122.3301", "37.3362,-121.8906", "61.2163,-149.8949"};
    private String[] reset = {"Reset"};
    private String Lat_Long = "33.421968,-111.936642";
    private JComboBox<String> cb = new JComboBox<String>(choices);
    private JComboBox<String> cb2 = new JComboBox<String>(reset);
    private JLabel lbl = new JLabel("Select a City");



    public Team4()  {    // constructor
      cb2.addActionListener(resetListener);
      cb.addActionListener(cityListener);
      comboPlate();
    }

    private void comboPlate(){
      GridLayout comboPanel = new GridLayout(1,2);
      setLayout(comboPanel);
      weatherPanel.setVisible(false);
      mapPanel.setVisible(false);
      this.removeAll();
      this.revalidate();
      this.repaint();
      this.add(cityPanel);
      cityPanel.setVisible(true);
      cityPlate();
    }

    private JPanel centerPlate() {
      GridLayout cp = new GridLayout(1,2);
      centerPanel.setLayout(cp);
      centerPanel.add(mapPanel);
      centerPanel.add(weatherPanel);
      mapPlate();
      try {
        weatherPlate();
      }
      catch(IOException e) {
        System.out.println("Error");
      }
      return centerPanel;
    }

    private void borderPlate() {
      cityPanel.setVisible(false);
      this.setLayout(new BorderLayout());
      this.removeAll();
      this.revalidate();
      this.repaint();
      this.add(buttonPanel, BorderLayout.SOUTH);
      this.add(centerPanel, BorderLayout.CENTER);
      weatherPanel.setVisible(true);
      mapPanel.setVisible(true);
      buttonPlate();
      centerPlate();
    }

    private JPanel buttonPlate() {
      cb2.addActionListener(resetListener);
      GridLayout buttonPlate = new GridLayout(1,1);
      buttonPanel.setLayout(buttonPlate);
      cb2.setVisible(true);
      buttonPanel.add(cb2);
      return buttonPanel;
    }

    private JPanel mapPlate(){
        try{
          JPanel mapPanel = new JPanel();
          mapPanel.setSize(250,250);

          String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?" + "center=" + Lat_Long + "&zoom=9&size=250x250&scale=1&maptype=roadmap";

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

        mapPanel.add(new JLabel(new ImageIcon((new ImageIcon("Team4Image.jpg")).getImage().getScaledInstance(125,125,java.awt.Image.SCALE_SMOOTH))));
        mapPanel.setVisible(true);
        return mapPanel;
    }



private JPanel weatherPlate() throws IOException, JSONException{
  weatherPanel.setLayout(new GridLayout(6,1));
  String yourKey = "cab82799b5b1e817dbccab51d6d7ec40";
  JSONObject json = readJsonFromUrl("https://api.darksky.net/forecast/" + yourKey + "/" + Lat_Long);

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

private JPanel cityPlate() {
  cityPanel.setSize(250,250);
  cityPanel.setVisible(true);
  lbl.setVisible(true);
  cityPanel.add(lbl);
  cb.setVisible(true);
  cityPanel.add(cb);
  return cityPanel;
}

ActionListener cityListener = new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
    String string = (String) cb.getSelectedItem();

    switch (string) {
      case "Phoenix":
        Lat_Long = coordinates[0];
        break;
      case "Los Angeles":
        Lat_Long = coordinates[1];
        break;
      case "San Diego":
        Lat_Long = coordinates[2];
        break;
      case "Nashville":
        Lat_Long = coordinates[3];
        break;
      case "Tampa":
        Lat_Long = coordinates[4];
        break;
      case "Austin":
        Lat_Long = coordinates[5];
        break;
      case "Portland":
        Lat_Long = coordinates[6];
        break;
      case "Seattle":
        Lat_Long = coordinates[7];
        break;
      case "San Jose":
        Lat_Long = coordinates[8];
        break;
      case "Anchorage":
        Lat_Long = coordinates[9];
        break;
      default:
    }
    borderPlate();
  }
};


ActionListener resetListener = new ActionListener() {
  @Override
  public void actionPerformed(ActionEvent e) {
    String string = (String) cb2.getSelectedItem();
    weatherPanel.removeAll();
    mapPanel.removeAll();
    comboPlate();
  }
};



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
    }
    finally {
      is.close();
    }
  }
}
