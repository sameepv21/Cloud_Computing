/* Weather
 * A class with current "complete"
 * weather information to use with the
 * WeatherServer and WeatherClient.
 */

import java.io.*;

public class Weather implements Serializable {

    private int temp;         // degrees celsius
    private int wind;         // m/s
    private boolean rain;     // raining - yes or no?
    private boolean stormy;     // stormy - yes or no?
    private boolean sun;      // sun is shinging - yes or no?
    private boolean clouds;   // clouds in the sky - yes or no?
    private boolean fog;      // foggy - yes or no?

    // Constructors

    public Weather(int temp,
		   int wind,
		   boolean rain,
		   boolean stormy,
		   boolean sun,
		   boolean clouds,
		   boolean fog) {
	this.temp = temp;
	this.wind = wind;
	this.rain = rain;
	this.stormy = stormy;
	this.sun = sun;
	this.clouds = clouds;
	this.fog = fog;
    }

    public Weather() {
	// Default values for the weather.
	temp = 28;
	wind = 20;
	rain = false;
	stormy = false;
	sun = true;
	clouds = false;
	fog = false;
    }

    // Accesors for the different types of weather.
    public int getWind() {
	return wind;
    }

    public int getTemperature() {
	return temp;
    }

    public String getWeatherType() {
	String w = "Today we have";
	boolean atLeastOneWeather = false;

	if (!rain && !stormy && !sun && !clouds && !fog)
	    w += " no weather at all!";
	else {	
	    if (rain) {
		w += " rain";
		atLeastOneWeather = true;
	    }

	    if (stormy) {
		if (atLeastOneWeather)
		    w += " and";
		else
		    atLeastOneWeather = true;
		w += " stormy";
	    }
	    
	    if (sun) {
		if (atLeastOneWeather)
		    w += " and";
		else
		    atLeastOneWeather = true;
		w += " sun";
	    }
	    
	    if (clouds) {
		if (atLeastOneWeather)
		    w += " and";
		else
		    atLeastOneWeather = true;
		w += " clouds";
	    }
	    
	    if (fog) {
		if (atLeastOneWeather)
		    w += " and";
		else
		    atLeastOneWeather = true;
		w += " fog";
	    }
	    
	    w += ".\n";
	}
	return w;
    }
	
    /* printWeather
     * Returns a string describing the weather.
     */
    public String printWeather() {
	String w = "The temperature is " + temp + " degrees Celsius\n";
	w += "The wind is blowing with " + wind + " m/s\n";
	
	if (rain)
	    w += "It is raining... \n";

	if (stormy)
	    w += "The strom is quite heavy.\n";

	if (sun)
	    w += "The sun is shining brightly.\n";

	if (clouds)
	    w += "There are some clouds in the sky.\n";

	if (fog)
	    w += "The air is white and cold. It is a bit foggy.\n";

	return w;
    }
}
