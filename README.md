# Thermometer

This is an application for the Android platform that is my final project. With the help of this software, it's possible to see the temperature and humidity of the environment in real-time on a mobile device. For this project, I used the ESP32 development board and the DHT22 sensor, and the Arduino code for this project is available in this repository.


## Environment Variables

To run this project, you will need to create a Supabase account and take API URL and API KEY and the create Credentials.h file in `Thermometer/Arduino/Thermometer-Arduino/src` directory and add the following  variables to your Credentials.h file

1 - `String SSID ="your wifi ssid";`

2 - `String PASSWORD ="your wifi password";`

3 - `String API_URL ="your api url from supabase dashboard";`

4 - `String API_KEY ="you api key from supabase dashboard";`

5 - `String temperatureTable  ="a table name for temperature with "celcius" (float type) and "fahrenheit" (float type) columns";`

6 - `String humidityTable  ="a table name for humidity with "percent" (float type) column"`

<h2>Screenshots</h2>
<table>
<tr>
    <td><img src="https://github.com/Ramink78/Thermometer/blob/develop/Screenshots/Temperature%20Screen%20Large.png?raw=true" alt="drawing" width="250"/></td>
    <td><img src="https://github.com/Ramink78/Thermometer/blob/develop/Screenshots/Humidity%20Screen%20Large.png?raw=true alt="drawing" width="250"/></td>
  </tr>
</table>
 