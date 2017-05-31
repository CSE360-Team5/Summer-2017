/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CSE360;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.*;
import java.awt.*;
import java.nio.charset.Charset;
import java.security.Security;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.*;


import org.json.*;


public class Team6  extends JPanel{
    
     public static String readThis(Reader rd) throws IOException {
StringBuilder sb = new StringBuilder();
int cp;
while((cp = rd.read()) != -1){
sb.append((char) cp);
}
return sb.toString();
}
public static JSONObject readURL(String url) throws IOException, JSONException{
InputStream in =  new URL(url).openStream();
try{
BufferedReader read = new BufferedReader(
new InputStreamReader(in, Charset.forName("UTF-8")));
String jsonText = readThis(read);
JSONObject jsn = new JSONObject(jsonText);
 
return jsn;
 
} finally {
 
in.close();
}
    }
        
        
    
    public Team6() {
    
try {
            
            /************************** Start try and catch **************************************/
            
           setPreferredSize(new Dimension(500,500));
           
            JSONObject jon = readURL("https://api.darksky.net/forecast/4f02d91363f259f5ca95263c5c032dfc/33.6744664,-112.1386462");
            String weather = jon.getJSONObject("currently").getString("summary");
            int temperature = jon.getJSONObject("currently").getInt("temperature");
            double humidity = jon.getJSONObject("currently").getDouble("humidity");
            double visibility = jon.getJSONObject("currently").getDouble("visibility");
            double dewPoint = jon.getJSONObject("currently").getDouble("dewPoint");
            

     
            String degree = "\u00b0";
            Font myFont = new Font("Serif", Font.BOLD, 12);   

               
            JLabel label = new JLabel(" Weather: " + weather);
            label.setFont(myFont);
            JLabel label2 = new JLabel("\n Temperature: " + Double.toString(temperature) + degree  + "F");
            label2.setFont(myFont);
            JLabel label3 = new JLabel("\n Humidity: " + Double.toString(humidity) + "%");
            label3.setFont(myFont);
            JLabel label4 = new JLabel("\n Visibility: " + Double.toString(visibility) + "%");
            label4.setFont(myFont);
            JLabel label5 = new JLabel("\n Dew point: " + Double.toString(dewPoint) + degree +  "F ");
            label5.setFont(myFont);

   String imageURL = "https://maps.googleapis.com/maps/api/staticmap?center=Tempe,CA&zoom=10&size=500x500";
   String destinationFile = "image.jpg";
   URL url = new URL(imageURL);
            java.io.InputStream in = url.openStream();
   OutputStream out = new FileOutputStream(destinationFile);
   byte b[] = new byte[2048];
   int lenght;
   while((lenght = in.read(b)) != -1){
out.write(b,0,lenght); //change
}
                
                in.close();
       out.close();
                
JPanel panel = new JPanel();
                panel.setPreferredSize(new Dimension(150, 150));
                panel.setVisible(true);
                JPanel panel2 = new JPanel();
                panel2.setPreferredSize(new Dimension(150,150));
                panel2.setVisible(true);

                panel2.add(label);
                panel2.add(label2);
                panel2.add(label3);
                panel2.add(label4);
                panel2.add(label5);
                
                
panel.add(new JLabel( new ImageIcon(( new ImageIcon("image.jpg")).getImage().getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH))));
                setLayout(new GridLayout(2,1,20,0));

                add(panel);
                add(panel2);
                
                
setVisible(true);

    
        } catch(IOException e){
                 System.exit(1);     
        } catch(JSONException e){
                 System.exit(1);       
        }
    }
    
}
