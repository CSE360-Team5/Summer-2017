package CSE360;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONException;
import org.json.JSONObject;

public class Team1 extends JPanel implements ActionListener {

	// LOCATION - TEMPE
	HashMap<String, String> cityLocMap = new HashMap<String, String>();
	private String[] loc = { "33.424564,-111.928001", "40.730610,-73.935242",
			"12.972442,77.580643", "45.444958,12.328463",
			"53.350140,-6.266155", "37.733795,-122.446747",
			"52.518623,13.376198", "51.501476,-0.140634",
			"19.451054,-99.125519", "28.644800,77.216721" };
	private String[] cities = { "TEMPE", "NEW_YORK_CITY", "BANGALORE",
			"VENICE", "DUBLIN", "SAN_FRANCISCO", "BERLIN", "LONDON",
			"MEXICO_CITY", "NEW_DELHI" };
	// WEATHER URL
	private final String W_HOST_URL = "https://api.darksky.net/";
	private final String W_PATH = "forecast/";
	private final String W_KEY = "b829783e844371d18cd29c0ffd19e42b/";

	// MAP URL
	private final String M_HOST_URL = "https://maps.googleapis.com/";
	private final String M_PATH = "maps/api/staticmap";
	private final String M_QUERY1 = "?center=";
	private final String M_QUERY2 = "&zoom=10&size=640x400&scale=2&maptype=roadmap";

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

	// Change Panel
	JPanel changePanel = new JPanel();
	String input = cities[0];

	public Team1() {
		setCityDetails();
		initialize();
	}

	private void initialize() {
		this.removeAll();
		list.add(Color.DARK_GRAY);
		list.add(Color.BLACK);
		list.add(Color.MAGENTA);
		list.add(Color.LIGHT_GRAY);
		setPanel();
		setChange();
package CSE360;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.json.JSONException;
import org.json.JSONObject;

public class Team1 extends JPanel implements ActionListener {

	// LOCATION - TEMPE
	HashMap<String, String> cityLocMap = new HashMap<String, String>();
	private String[] loc = { "33.424564,-111.928001", "40.730610,-73.935242",
			"12.972442,77.580643", "45.444958,12.328463",
			"53.350140,-6.266155", "37.733795,-122.446747",
			"52.518623,13.376198", "51.501476,-0.140634",
			"19.451054,-99.125519", "28.644800,77.216721" };
	private String[] cities = { "TEMPE", "NEW_YORK_CITY", "BANGALORE",
			"VENICE", "DUBLIN", "SAN_FRANCISCO", "BERLIN", "LONDON",
			"MEXICO_CITY", "NEW_DELHI" };
	// WEATHER URL
	private final String W_HOST_URL = "https://api.darksky.net/";
	private final String W_PATH = "forecast/";
	private final String W_KEY = "b829783e844371d18cd29c0ffd19e42b/";

	// MAP URL
	private final String M_HOST_URL = "https://maps.googleapis.com/";
	private final String M_PATH = "maps/api/staticmap";
	private final String M_QUERY1 = "?center=";
	private final String M_QUERY2 = "&zoom=10&size=640x400&scale=2&maptype=roadmap";

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

	// Change Panel
	JPanel changePanel = new JPanel();
	String input = cities[0];

	public Team1() {
		setCityDetails();
		initialize();
	}

	private void initialize() {
		this.removeAll();
		list.add(Color.DARK_GRAY);
		list.add(Color.BLACK);
		list.add(Color.MAGENTA);
		list.add(Color.LIGHT_GRAY);
		setPanel();
		setChange();
		if (cityLocMap.get(input) != null) {
			setImage(cityLocMap.get(input));
			setReport(cityLocMap.get(input));
		}
	}

	private void setChange() {
		changePanel.removeAll();
		JButton button = new JButton("Select city");
		changePanel.add(button);
		button.addActionListener(this);
	}

	private void setOptionPane() {
		String message = "Select a location to get information";
		String title = "Location Details";
		input = (String) JOptionPane.showInputDialog(this, message, title,
				JOptionPane.QUESTION_MESSAGE, null, cities, cities[0]);

	}

	private void setCityDetails() {
		for (int i = 0; i < 10; i++) {
			cityLocMap.put(cities[i], loc[i]);
		}
	}

	private void setPanel() {
		GridLayout layout = new GridLayout(1, 3);
		setLayout(layout);
		add(imagePanel);
		add(weatherPanel);
		add(changePanel);
		imagePanel.setBackground(Color.GRAY);

	}

	private void setReport(String Location) {
		weatherPanel.removeAll();
		try {
			JSONObject jsonObj = getWeatherInfoFromURL(W_HOST_URL + W_PATH
					+ W_KEY + Location);
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

	private void setImage(String Location) {
		imagePanel.removeAll();
		getMapFromURL(M_HOST_URL + M_PATH + M_QUERY1 + Location + M_QUERY2,
				FILE_NAME);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		setOptionPane();
		initialize();
		revalidate();
		repaint();
	}
}
	}

	private void setChange() {
		changePanel.removeAll();
		JButton button = new JButton("Select city");
		changePanel.add(button);
		button.addActionListener(this);
	}

	private void setOptionPane() {
		String message = "Select a location to get information";
		String title = "Location Details";
		input = (String) JOptionPane.showInputDialog(this, message, title,
				JOptionPane.QUESTION_MESSAGE, null, cities, cities[0]);

	}

	private void setCityDetails() {
		for (int i = 0; i < 10; i++) {
			cityLocMap.put(cities[i], loc[i]);
		}
	}

	private void setPanel() {
		GridLayout layout = new GridLayout(1, 3);
		setLayout(layout);
		add(imagePanel);
		add(weatherPanel);
		add(changePanel);
		imagePanel.setBackground(Color.GRAY);

	}

	private void setReport(String Location) {
		weatherPanel.removeAll();
		try {
			JSONObject jsonObj = getWeatherInfoFromURL(W_HOST_URL + W_PATH
					+ W_KEY + Location);
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

	private void setImage(String Location) {
		imagePanel.removeAll();
		getMapFromURL(M_HOST_URL + M_PATH + M_QUERY1 + Location + M_QUERY2,
				FILE_NAME);
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

	@Override
	public void actionPerformed(ActionEvent e) {
		setOptionPane();
		initialize();
		revalidate();
		repaint();
	}
}