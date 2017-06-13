package CSE360;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.json.JSONException;
import org.json.JSONObject;

public class Team1 extends JPanel {
	// LOCATION - TEMPE
	private final String TEMPE_LOCATION = "33.424564,-111.928001";

	// WEATHER URL
	private final String W_HOST_URL = "https://api.darksky.net/";
	private final String W_PATH = "forecast/";
	private final String W_KEY = "b829783e844371d18cd29c0ffd19e42b/";

	// MAP URL
	private final String M_HOST_URL = "https://maps.googleapis.com/";
	private final String M_PATH = "maps/api/staticmap";
	private final String M_QUERY = "?center=" + TEMPE_LOCATION
			+ "&zoom=16&size=640x400&scale=2&maptype=roadmap";

	// Weather Data
	private final String[] elements = { "timezone", "summary", "temperature",
			"humidity" };
	private final String[] eleDisc = { " Time Zone: ", " Summary: ",
			" Temparature: ", " Humidity: " };
	JPanel weatherPanel = new JPanel();

	// Map Data
	private final static String FILE_NAME = "Team1Map.jpg";
	JPanel imagePanel = new JPanel();

	// Color mangement
	ArrayList<Color> list = new ArrayList<Color>();
	int number = 0;

	public Team1() {
		list.add(Color.DARK_GRAY);
		list.add(Color.BLACK);
		list.add(Color.MAGENTA);
		list.add(Color.LIGHT_GRAY);
		setPanel();
		setImage();
		setReport();
	}

	private void setPanel() {
		GridLayout layout = new GridLayout();
		setLayout(layout);
		add(imagePanel);
		add(weatherPanel);
		imagePanel.setBackground(Color.GRAY);

  }

	private void setReport() {
		try {
			JSONObject jsonObj = getWeatherInfoFromURL(W_HOST_URL + W_PATH
					+ W_KEY + TEMPE_LOCATION);
			GridLayout wPanelLayout = new GridLayout(5, 1);
			weatherPanel.setLayout(wPanelLayout);
			JSONObject currentlyObject = (JSONObject) jsonObj.get("currently");
			addLabelForJSON(weatherPanel, eleDisc[0], jsonObj, elements[0]);
			for (int i = 1; i < elements.length; i++)
				addLabelForJSON(weatherPanel, eleDisc[i], currentlyObject,
						elements[i]);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void setImage() {
		getMapFromURL(M_HOST_URL + M_PATH + M_QUERY, FILE_NAME);
		JLabel imageLabel = new JLabel(new ImageIcon((new ImageIcon(FILE_NAME))
				.getImage().getScaledInstance(330, 227, Image.SCALE_SMOOTH)));
		imagePanel.add(imageLabel);
	}

	private JSONObject getWeatherInfoFromURL(String string) {
		try {
			URL url = new URL(string);
			URLConnection connection = url.openConnection();
			connection.connect();
			BufferedReader bReader = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), Charset.forName("UTF-8")));
			StringBuilder sBuilder = new StringBuilder();
			String temp;
			while ((temp = bReader.readLine()) != null) {
				sBuilder.append(temp);
			}
			JSONObject jsonResult;
			jsonResult = new JSONObject(sBuilder.toString());
			bReader.close();
			return jsonResult;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}

	private void getMapFromURL(String string, String fileName) {
		try {
			URL url = new URL(string);
			URLConnection connection = url.openConnection();
			connection.connect();
			InputStream inStream = connection.getInputStream();
			OutputStream outStream = new FileOutputStream(fileName);
			byte[] temp = new byte[1024];
			int len;
			while ((len = inStream.read(temp)) != -1) {
				outStream.write(temp, 0, len);
			}
			inStream.close();
			outStream.close();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void addLabelForJSON(JPanel panel, String string,
			JSONObject jsonObj, String string2) {
		JLabel label = new JLabel();
		try {
			label.setText(string + jsonObj.get(string2));
			label.setForeground(randomColor());
			panel.add(label);
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

	private Color randomColor() {
		number++;
		return list.get(number % 4);
	}
}