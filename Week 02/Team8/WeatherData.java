package CSE360;

public class WeatherData {
	private String summary;
	private Double windSpeed;
	private Double temperature;
	private Double humidity;
//	private Double e;
	
	public WeatherData(String _a, Double _b, Double _c, Double _d){
		this.setSummary(_a);
		this.setWindSpeed(_b);
		this.setTemperature(_c);
		this.setHumidity(_d);
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String a) {
		this.summary = a;
	}
	public Double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(Double b) {
		this.windSpeed = b;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double c) {
		this.temperature = c;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double d) {
		this.humidity = d;
	}
//	public Double getE() {
//		return e;
//	}
//	public void setE(Double e) {
//		this.e = e;
//	}
	public String getSentence() {
		String S = "Weather: " + this.getSummary() + "\n" +
  			  "Temperature(f): " + this.getTemperature() + "\n" +
  			  "Wind Speed(m): " + this.getWindSpeed() + "\n" +
  			  "Humidity: " + this.getHumidity() + "\n" ;
		return S;
	}
	
}
