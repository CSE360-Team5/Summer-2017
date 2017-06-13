package CSE360;

import java.awt.*;
import javax.swing.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Scanner;
import java.nio.charset.Charset;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;

import org.json.JSONException;
import org.json.JSONObject;

public class Team5 extends JPanel {

	public Team5() {

		// Creates main panel to put two panels into
		JPanel main = new JPanel(new GridLayout(1, 2));

		// Adds Weather and Google panels to the main panel
		main.add(Weather());
		main.add(Google());

		add(main);
	}

	public JPanel Weather() {

		// New Panel weather
		JPanel weather = new JPanel();

		try {
			JSONObject json = readJsonFromUrl(
					"https://api.darksky.net/forecast/f657e7aed849b4520c258bb7bd2f093c/32.7357,-97.1081");
			System.out.println(json.getJSONObject("currently").getString("summary"));

			JLabel j1 = new JLabel("visibility:");

			String n0 = json.getJSONObject("currently").getString("summary");
			String n1 = "Visibility: " + json.getJSONObject("currently").getDouble("visibility");
			String n2 = "humidity: " + json.getJSONObject("currently").getDouble("humidity");
			String n3 = "Temperature: " + json.getJSONObject("currently").getDouble("apparentTemperature");
			String n4 = "WindSpeed: " + json.getJSONObject("currently").getDouble("windSpeed");
			String n5 = "CloudCover: " + json.getJSONObject("currently").getDouble("cloudCover");

			String strMsg = "<html><body>" + "Arlington, TX:  " + n0 + "<br>" + n3 + "<br>" + n2 + "<br>" + n1 + "<br>"
					+ n4 + "<br>" + n5 + "<body></html>";

			j1.setText(strMsg);

			weather.add(j1);

		} catch (IOException e) {
			System.exit(1);
		}

		// Add Border
		weather.setBorder(BorderFactory.createLineBorder(Color.blue, 3));

		// Set Background
		weather.setBackground(Color.white);

		// Returns JPanel
		return weather;
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

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();

	}

	public JPanel Google() {

		// New panel map
		JPanel map = new JPanel();

		// Used if you want to have user input
		Scanner reader = new Scanner(System.in);
		try {

			double lng = 0.0;
			double lat = 0.0;
			int zoom = 0;

			lng = 32.7357; // reader.nextDouble();
			lat = -97.1081; // reader.nextDouble();
			zoom = 16; // reader.nextInt();

			// Arlington, TX long = 32.7357 lat = -97.1081 with zoom = 15
			// Austin, TX long = 30.2731851 lat = -97.7424588 with zoom = 13
			// New York, NY long = 40.714728 lat = -73.998672 with zoom = 11

			// Image url
			String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?" + "center=" + lng + "," + lat + "&zoom="
					+ zoom + "&size=640x640&maptype=road";

			String destinationFile = "image.jpg";
			URL url = new URL(imageUrl);
			InputStream is = url.openStream();
			OutputStream os = new FileOutputStream(destinationFile);

			byte[] b = new byte[2048];
			int length;

			while ((length = is.read(b)) != -1) {
				os.write(b, 0, length);
			}

			is.close();
			os.close();
			reader.close();

		} catch (IOException e) {
			System.exit(1);
		}

		map.setPreferredSize(new Dimension(245, 245));

		// Adds map to map Panel
		map.add(new JLabel(new ImageIcon(
				(new ImageIcon("image.jpg")).getImage().getScaledInstance(250, 250, java.awt.Image.SCALE_SMOOTH))));

		// Returns map panel
		return map;
	}
}
