/*
 * 	CSE 360 - Summer 2017
 * 	Team Members:
 * 		+ Amit Ranjan
 * 		+ Bahar Shahrokhian Ghahfarokhi
 * 		+ Michael Ostaszewski
 * 		+ Yaqoub Alyakoob
 */

package CSE360;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.*;

public class Team8 extends JPanel {

	HashMap<String, CityDetail> hash;
	String[] cityNames;

	CityDetail selectedCity = null;
	int rightMargin = 20;

	JTextArea text;
	JLabel mapLabel;
	JButton optionsButton;
	JPanel mapPanel, footerPanel, weatherPanel;
	ImageIcon OptionsIcon, WeatherIcon, VisibilityIcon, HumidityIcon, CloudCoverIcon, WindSpeedIcon;
	Font font;

	public Team8() {
		Initialize();
		ShowDialog();
	}

	private void ShowDialog() {
		String newCity = (String) JOptionPane.showInputDialog(this, "Select a city:", "City Options", JOptionPane.OK_CANCEL_OPTION, null, cityNames, selectedCity);
		if(selectedCity == null || newCity != selectedCity.CityName()) {
			selectedCity = hash.get(newCity);
			UpdatePanel();
		}
	}

	private void UpdatePanel() {
		
		this.removeAll();

		GoogleMaps googleMap = new GoogleMaps(selectedCity.Latitude(), selectedCity.Longitude());
		
		if(this.getWidth() > 0)
			mapLabel = new JLabel(new ImageIcon((googleMap.getImage()).getImage().getScaledInstance(mapPanel.getWidth(), mapPanel.getHeight(), Image.SCALE_SMOOTH)));
		else
			mapLabel = new JLabel(googleMap.getImage());
		mapPanel = new JPanel(new BorderLayout());
		mapPanel.add(mapLabel, BorderLayout.CENTER);
		footerPanel = new JPanel();
		footerPanel.setBackground(Color.WHITE);
		footerPanel.setLayout(new BorderLayout());
		footerPanel.add(new JLabel(WeatherIcon), BorderLayout.WEST);
		
		weatherPanel = new JPanel();
		weatherPanel.setLayout(new GridLayout(2, 5));
		weatherPanel.setBackground(Color.WHITE);
		
		Weather weather = new Weather(selectedCity);
		JLabel temperatureLabel = new JLabel("" + weather.weatherData.getTemperature() + "°F");
		temperatureLabel.setForeground(Color.BLUE);
		temperatureLabel.setFont(font);
		temperatureLabel.setToolTipText("" + weather.weatherData.getTemperature() + "°F");
		
		JLabel summaryLabel = new JLabel(weather.weatherData.getSummary());
		summaryLabel.setFont(font);
		summaryLabel.setToolTipText(weather.weatherData.getSummary());
		weatherPanel.add(temperatureLabel);
		
		AddWeatherComponent(VisibilityIcon, weather.weatherData.getVisibility(), "Visibility");
		AddWeatherComponent(HumidityIcon, weather.weatherData.getHumidity(), "Humidity");
		weatherPanel.add(summaryLabel);
		AddWeatherComponent(CloudCoverIcon, weather.weatherData.getCloudCover(), "Cloud Cover");
		AddWeatherComponent(WindSpeedIcon, weather.weatherData.getWindSpeed(), "Wind Speed");
		
		footerPanel.add(weatherPanel, BorderLayout.CENTER);

		optionsButton = new JButton();
		optionsButton.setBackground(Color.WHITE);
		optionsButton.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, rightMargin));
		optionsButton.setContentAreaFilled(false);
		optionsButton.setFocusPainted(false);
		optionsButton.setIcon(OptionsIcon);
		optionsButton.setToolTipText("Options");
		optionsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ShowDialog();
			}
		});
		JPanel optionsPanel = new JPanel();
		optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.LINE_AXIS));
		optionsPanel.setBackground(Color.WHITE);
		optionsPanel.add(optionsButton);
		footerPanel.add(optionsPanel, BorderLayout.EAST);

		this.setLayout(new BorderLayout());
		this.add(mapPanel, BorderLayout.CENTER);
		this.add(footerPanel, BorderLayout.SOUTH);

		this.addComponentListener(new ComponentListener() {

			@Override
			public void componentShown(ComponentEvent e) {}

			@Override
			public void componentResized(ComponentEvent e) {
				mapLabel.setIcon(new ImageIcon((googleMap.getImage()).getImage().getScaledInstance(e.getComponent().getWidth(), e.getComponent().getHeight(), Image.SCALE_SMOOTH)));
			}

			@Override
			public void componentMoved(ComponentEvent e) {}

			@Override
			public void componentHidden(ComponentEvent e) {}
		});
		this.revalidate();
		this.repaint();
	}
	
	private void AddWeatherComponent(ImageIcon imgIcon, double weatherDataValue, String componentTooltip) {
		JLabel imgLabel = new JLabel(imgIcon);
		imgLabel.setToolTipText(componentTooltip);
		weatherPanel.add(imgLabel);
		JLabel weatherComponentLabel = new JLabel("" + weatherDataValue);
		weatherComponentLabel.setToolTipText("" + weatherDataValue);
		weatherComponentLabel.setFont(font);
		weatherComponentLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, rightMargin));
		weatherPanel.add(weatherComponentLabel);
	}

	private void Initialize() {

		//Construct an array of cities
		hash = new HashMap<>();
		hash.put("New York", new CityDetail("New York", 40.7128, -74.0059));
		hash.put("Las Vegas", new CityDetail("Las Vegas", 36.1699, -115.1398));
		hash.put("New Orleans", new CityDetail("New Orleans", 29.9511, -90.0715)); 
		hash.put("Phoenix", new CityDetail("Phoenix", 33.4484, -112.0740)); 
		hash.put("Los Angeles", new CityDetail("Los Angeles", 34.0522, -118.2437)); 
		hash.put("San Francisco", new CityDetail("San Francisco", 37.7749, -122.4194)); 
		hash.put("Chicago", new CityDetail("Chicago", 41.8781, -87.6298)); 
		hash.put("Seattle", new CityDetail("Seattle", 47.6062, -122.3321)); 
		hash.put("Miami", new CityDetail("Miami", 25.7617, -80.1918)); 
		hash.put("San Diego", new CityDetail("San Diego", 32.7157, -117.1611));

		Set<String> keySet = hash.keySet();
		cityNames = keySet.toArray(new String[keySet.size()]);

		//Fetch icons for various purposes...
		int defaultIconWidth = 50, defaultIconHeight = 50;
		OptionsIcon = GetImageFromUrl("https://cdn2.iconfinder.com/data/icons/flat-ui-icons-24-px/24/settings-24-512.png", defaultIconWidth, defaultIconWidth);
		WeatherIcon = GetImageFromUrl("http://www.concordiaks.org/images/weather_20icon.png", defaultIconWidth*3, defaultIconHeight*3);
		VisibilityIcon = GetImageFromUrl("https://d30y9cdsu7xlg0.cloudfront.net/png/118188-200.png", defaultIconWidth, defaultIconHeight);
		HumidityIcon = GetImageFromUrl("https://d30y9cdsu7xlg0.cloudfront.net/png/1001987-200.png", defaultIconWidth, defaultIconHeight);
		CloudCoverIcon = GetImageFromUrl("https://cdn1.iconfinder.com/data/icons/simple-icons/4096/skydrive-4096-black.png", defaultIconWidth, defaultIconHeight);
		WindSpeedIcon = GetImageFromUrl("https://cdn4.iconfinder.com/data/icons/weather-conditions/512/wind-512.png", defaultIconWidth, defaultIconHeight);
		
		font = new Font("Calibri", Font.BOLD | Font.ITALIC, 30);
	}

	private static ImageIcon GetImageFromUrl(String urlString, int width, int height) {
		ImageIcon img = null;
		try {
			URL url = new URL(urlString);
			BufferedImage bufimg = ImageIO.read(url);
			img = new ImageIcon(bufimg.getScaledInstance(width, height, Image.SCALE_SMOOTH));
		} catch (IOException e) {
			System.exit(1);
		}
		return img;
	}
	
	///////////////////////////////////////////////////////////
	
	public class CityDetail {
		private String cityName;
		private double latitude;
		private double longitude;

		public CityDetail(String cityName, double latitude, double longitude) {
			this.cityName = cityName;
			this.latitude = latitude;
			this.longitude = longitude;
		}

		public String CityName() {
			return cityName;
		}
		public double Latitude() {
			return latitude;
		}
		public double Longitude() {
			return longitude;
		}
		public String toString() {
			return cityName;
		}
	}
	
	///////////////////////////////////////////////////////////
	
	public class GoogleMaps {

		private String api_url = "https://maps.googleapis.com/maps/api/staticmap?";
		private String api_key = "AIzaSyCPBkB4FfvXzIYRgbRBmAMxqXLhnaGJVXU";
		private String zoom = "zoom=11&";
		private String size = "size=612x612&";
		private String scale = "scale=1&";
		private String mapType = "maptype=roadmap&";
		ImageIcon img;

		public GoogleMaps(double latitude, double longitude) {

			String center = "center=" + latitude +"," + longitude + "&";

			api_url = api_url + center + zoom + size + scale + mapType + "key=" + api_key;

			try {
				URL url = new URL(api_url);
				BufferedImage bufimg = ImageIO.read(url);
				img = new ImageIcon(bufimg);
			} catch (IOException e) {
				System.exit(1);
			}
		}

		public ImageIcon getImage() {
			return img;
		}
	}
	
	///////////////////////////////////////////////////////////
	
	public class Weather{
		
		WeatherData weatherData;

		Weather(CityDetail city)
		{
			this._run(city);
		}

		private void _run(CityDetail city)
		{
			try 
			{
				String yourKey = "fdd8a0537b1af5efefd850be6d1e3488"; 
				JSONObject json = readJsonFromUrl("https://api.darksky.net/forecast/"
						+yourKey+"/" + city.Latitude() + "," + city.Longitude());
				String summary = json.getJSONObject("currently").getString("summary");
				double windSpeed = json.getJSONObject("currently").getDouble("windSpeed");
				double temperature = json.getJSONObject("currently").getDouble("temperature");
				double humidity = json.getJSONObject("currently").getDouble("humidity");
				double visibility = json.getJSONObject("currently").getDouble("visibility");
				double cloudCover = json.getJSONObject("currently").getDouble("cloudCover");
				weatherData = new WeatherData(summary, windSpeed, temperature, humidity, visibility, cloudCover); 
			} 
			catch (IOException | JSONException ex) 
			{
				Logger.getLogger(Weather.class.getName()).log(Level.SEVERE, null, ex);
				System.exit(1);
			}
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
	}
	
	//////////////////////////////////////////////////////////
	
	public class WeatherData {
		private String summary;
		private double windSpeed;
		private double temperature;
		private double humidity;
		private double visibility;
		private double cloudCover;

		public WeatherData(String summary, double windSpeed, double temperature, double humidity, double visibility, double cloudCover) {
			this.setSummary(summary);
			this.setWindSpeed(windSpeed);
			this.setTemperature(temperature);
			this.setHumidity(humidity);
			this.setVisibility(visibility);
			this.setCloudCover(cloudCover);
		}

		public String getSummary() {
			return summary;
		}
		public void setSummary(String summary) {
			this.summary = summary;
		}
		public double getWindSpeed() {
			return windSpeed;
		}
		public void setWindSpeed(double windSpeed) {
			this.windSpeed = windSpeed;
		}
		public double getTemperature() {
			return temperature;
		}
		public void setTemperature(double temperature) {
			this.temperature = temperature;
		}
		public double getHumidity() {
			return humidity;
		}
		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}
		public double getVisibility() {
			return visibility;
		}
		public void setVisibility(double visibility) {
			this.visibility = visibility;
		}
		public double getCloudCover() {
			return cloudCover;
		}
		public void setCloudCover(double cloudCover) {
			this.cloudCover = cloudCover;
		}

	}

}