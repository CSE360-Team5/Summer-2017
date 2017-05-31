package CSE360;
import java.awt.*;
import java.io.*;
import java.net.URL;
import javax.swing.*;
import org.json.*;
import java.net.*;

public class Team2 extends JPanel{
    
    // Nested class for gradient BG
    public class JPane extends JLabel {
        public JPane() {
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            LinearGradientPaint lgp = new LinearGradientPaint(
                    new Point(0, 0), 
                    new Point(0, getHeight()), 
                    new float[]{0.1f, 0.3f, 0.9f}, 
                    new Color[]{new Color(220, 220, 255), new Color(150, 150, 210), new Color(40, 40, 100)});
            g2d.setPaint(lgp);
            g2d.fill(new Rectangle(0, 0, getWidth(), getHeight()));
            g2d.dispose();
            super.paintComponent(g);
        }
    }
    
    public Team2() {            
            try {
            	/*-------------------------------GoogleMap--------------------------------*/
                // Parameter Declarations
                String gKey = "AIzaSyCz92cudhuiKxqRG-AEFPHbEXk-6H_R9eM";
                String mapType = "roadmap";
                double lat = 33.4255;
                double lon = -111.9400;
                int mapWidth = 250;
                int mapHeight = 55;     
                int scale = 2; 
                // Concatenate URL
                String mapUrl = "https://maps.googleapis.com/maps/api/staticmap?" 
                            + "center=" + lat +"," + lon 
                            + "&zoom=11&size=" + mapWidth + "x" + mapHeight 
                            + "&scale=" + scale + "&maptype=" + mapType + "&key=" 
                            + gKey;
                String fileName = "result.jpg";
                URL url = new URL(mapUrl);               
                // Open IO Streams
                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(fileName);

                byte[] b = new byte[2048];
                int length;
                
                // Read one byte of image data and write it to output
                while ((length = is.read(b)) != -1) {
                    os.write(b, 0, length);
                }
                is.close();
                os.close();
                
                // Add map image as label
                JLabel map = new JLabel(new ImageIcon((new ImageIcon(fileName)).getImage().getScaledInstance(mapWidth, mapHeight, java.awt.Image.SCALE_SMOOTH)));
                
                /*---------------------------DarkskyWeather-------------------------------*/
                String wKey = "29fd0065da58c853121182640d464df8";
                String weaUrl = "https://api.darksky.net/forecast/" + wKey + "/" + lat + "," + lon;
                // Connect to darksky.net
                url = new URL(weaUrl);
                URLConnection wc = url.openConnection();
                BufferedReader in = new BufferedReader(
                                    new InputStreamReader(
                                    wc.getInputStream()));
                String inputLine;
                String json = "";                
                // Read response and add to string
                while ((inputLine = in.readLine()) != null) {
                    json = json + inputLine;
                }
                // Parse JSON
                JSONObject obj = new JSONObject(json);
                in.close();
                
                //5 Weather info: Temperature, Visibility, WindSpeed, Humidity, DewPoint
                Double temp = obj.getJSONObject("currently").getDouble("temperature");
                
                Font general = new Font("Sans Serif", Font.BOLD, 14);
                int color_red = temp.intValue() * 2;
                if (color_red > 255) {
                    color_red = 255;                   
                } else if (color_red < 0) {
                    color_red = 0;
                }
                int color_blue = 255 - color_red;
                JLabel  l_temp = new JLabel (String.valueOf(temp));
                l_temp.setForeground(new Color(color_red, 50, color_blue));                
                l_temp.setBackground(Color.LIGHT_GRAY);
                l_temp.setFont(new Font("Sans Serif", Font.BOLD, 16));
                
                int vis = obj.getJSONObject("currently").getInt("visibility");
                JLabel l_vis = new JLabel (String.valueOf(vis));
                l_vis.setFont(general);
                l_vis.setForeground(Color.WHITE);
                double ws = obj.getJSONObject("currently").getDouble("windSpeed");
                JLabel l_ws = new JLabel (String.valueOf(ws));
                l_ws.setForeground(Color.WHITE); 
                l_ws.setFont(general);
                double hum = obj.getJSONObject("currently").getDouble("humidity");
                JLabel l_hum = new JLabel (String.valueOf(hum));
                l_hum.setForeground(Color.WHITE);
                l_hum.setFont(general);
                double dew = obj.getJSONObject("currently").getDouble("dewPoint");
                JLabel l_dew = new JLabel (String.valueOf(dew));
                l_dew.setFont(general);
                l_dew.setForeground(Color.WHITE); 
             
                String filePath = "src/CSE360/imagesTeam2/";
                JLabel pic = new JLabel(new ImageIcon((new ImageIcon(filePath+(obj.getJSONObject("currently").getString("icon") + ".gif"))).getImage().getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH)));
                JLabel icon_vis = new JLabel(new ImageIcon((new ImageIcon(filePath+"visibility.png")).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
                JLabel icon_temp = new JLabel(new ImageIcon((new ImageIcon(filePath+"temperature.png")).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
                JLabel icon_ws = new JLabel(new ImageIcon((new ImageIcon(filePath+"windSpeed.png")).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
                JLabel icon_hum = new JLabel(new ImageIcon((new ImageIcon(filePath+"humidity.png")).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
                JLabel icon_dew = new JLabel(new ImageIcon((new ImageIcon(filePath+"dewPoint.png")).getImage().getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH)));
                
                //Create a panel for all Weather info
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                    System.exit(1);
                }
                JPane Weather = new JPane();                
                
                //Use SpringLayout
                SpringLayout layout = new SpringLayout();
                Weather.setLayout(layout);
                Weather.setBackground(Color.LIGHT_GRAY);
                Weather.setSize(200, 70);
                Weather.add(pic);                
                layout.putConstraint(SpringLayout.WEST, pic, 2, SpringLayout.WEST, Weather);
                layout.putConstraint(SpringLayout.NORTH, pic, 0, SpringLayout.NORTH, Weather);
                Weather.add(icon_temp);
                layout.putConstraint(SpringLayout.WEST, icon_temp, 2, SpringLayout.EAST, pic);
                layout.putConstraint(SpringLayout.NORTH, icon_temp, 0, SpringLayout.NORTH, Weather);
                Weather.add(icon_vis);
                layout.putConstraint(SpringLayout.WEST, icon_vis, 2, SpringLayout.EAST, pic);
                layout.putConstraint(SpringLayout.NORTH, icon_vis, 0, SpringLayout.SOUTH, icon_temp);
                Weather.add(icon_ws);
                layout.putConstraint(SpringLayout.WEST, icon_ws, 0, SpringLayout.EAST, l_temp);
                layout.putConstraint(SpringLayout.NORTH, icon_ws, 0, SpringLayout.NORTH, Weather);
                Weather.add(icon_hum);
                layout.putConstraint(SpringLayout.WEST, icon_hum, 0, SpringLayout.EAST, l_vis);
                layout.putConstraint(SpringLayout.NORTH, icon_hum, 0, SpringLayout.SOUTH, icon_ws);
                Weather.add(icon_dew);
                layout.putConstraint(SpringLayout.WEST, icon_dew, 0, SpringLayout.EAST, l_ws);
                layout.putConstraint(SpringLayout.NORTH, icon_dew, 0, SpringLayout.NORTH, Weather);
                
                Weather.add(l_temp);
                layout.putConstraint(SpringLayout.WEST, l_temp, 0, SpringLayout.EAST, icon_temp);
                layout.putConstraint(SpringLayout.NORTH, l_temp, 0, SpringLayout.NORTH, Weather);
                Weather.add(l_vis);
                layout.putConstraint(SpringLayout.WEST, l_vis, 0, SpringLayout.EAST, icon_vis);
                layout.putConstraint(SpringLayout.NORTH, l_vis, 0, SpringLayout.SOUTH, l_temp);
                Weather.add(l_ws);
                layout.putConstraint(SpringLayout.WEST, l_ws, 0, SpringLayout.EAST, icon_ws);
                layout.putConstraint(SpringLayout.NORTH, l_ws, 2, SpringLayout.NORTH, Weather);
                Weather.add(l_hum);
                layout.putConstraint(SpringLayout.WEST, l_hum, 0, SpringLayout.EAST, icon_hum);
                layout.putConstraint(SpringLayout.NORTH, l_hum, 2, SpringLayout.SOUTH, l_ws);
                Weather.add(l_dew);
                layout.putConstraint(SpringLayout.WEST, l_dew, 0, SpringLayout.EAST, icon_dew);
                layout.putConstraint(SpringLayout.NORTH, l_dew, 2, SpringLayout.NORTH, Weather);
                
                //Create a whole panel that contains all
                JPanel all = new JPanel();
                all.setLayout(new GridLayout(2,1));
                all.add(map);
                all.add(Weather);
                add(all);
                
            } catch (IOException e) {
                System.exit(1);
            } catch (JSONException e) {
                System.exit(1);                
            }      
        }   
    
}

