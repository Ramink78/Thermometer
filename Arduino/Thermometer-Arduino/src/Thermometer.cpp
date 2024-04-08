#include <Arduino.h>
#include <WiFiClientSecure.h>
#include <HTTPClient.h>
#include <WiFi.h>
#include <DHT.h>
#include <Credentials.h>
uint8_t DHTPin = 32; 
#define DHTTYPE DHT22   // DHT 22  (AM2302), AM2321
DHT dht22(DHTPin, DHTTYPE);                



const int httpsPort = 443;
int sendinginterval = 1200; 

HTTPClient https;
WiFiClientSecure client;

float Temperature;
float Humidity;

 void sendHumidity(float percent);
 void sendTemperature(float celcius,float fahrenheit);
void setup() {
    Serial.begin(115200);
    pinMode(DHTPin, INPUT);
    dht22.begin();              
  client.setInsecure();

  Serial.print("Connecting to WiFi");
  WiFi.begin(SSID, PASSWORD);
  while (WiFi.status() != WL_CONNECTED) {
    delay(100);
    Serial.print(".");
  }

  Serial.println("");
  Serial.println("WiFi connected.");
  Serial.println("IP address: ");
  Serial.println(WiFi.localIP());
}

void loop() {
  // read humidity
  float humi  = dht22.readHumidity();
  // read temperature in Celsius
  float tempC = dht22.readTemperature();
  // read temperature in Fahrenheit
  float tempF = dht22.readTemperature(true);

  // check whether the reading is successful or not
  if ( isnan(tempC) || isnan(tempF) || isnan(humi)) {
    Serial.println("Failed to read from DHT22 sensor!");
  } else {
    Serial.print("Humidity: ");
    Serial.print(humi);
    Serial.print("%");

    Serial.print("  |  ");

    Serial.print("Temperature: ");
    Serial.print(tempC);
    Serial.print("°C  ~  ");
    Serial.print(tempF);
    Serial.println("°F");
  }

   if (WiFi.status() == WL_CONNECTED) {
    sendTemperature(tempC, tempF);
    sendHumidity(humi);

    }else{
      Serial.println("Error in WiFi connection");
    }
  delay(2000);
}
void sendTemperature(float celcius,float fahrenheit){
      https.begin(client,API_URL+"/rest/v1/"+temperatureTable);
      https.addHeader("Content-Type", "application/json");
      https.addHeader("Prefer", "return=representation");
      https.addHeader("apikey", API_KEY);
      https.addHeader("Authorization", "Bearer " + API_KEY);
      int httpCode = https.POST("{\"celcius\":" + String(celcius)+","+ "\"fahrenheit\":"+String(fahrenheit)+ "}");
      String payload = https.getString();
      Serial.println(httpCode);   //Print HTTP return code
      Serial.println(payload);    //Print request response payload
      https.end();

}
void sendHumidity(float percent){
      https.begin(client,API_URL+"/rest/v1/"+humidityTable);
      https.addHeader("Content-Type", "application/json");
      https.addHeader("Prefer", "return=representation");
      https.addHeader("apikey", API_KEY);
      https.addHeader("Authorization", "Bearer " + API_KEY);
      int httpCode = https.POST("{\"percent\":" + String(percent)+ "}");
      String payload = https.getString();
      Serial.println(httpCode);   //Print HTTP return code
      Serial.println(payload);    //Print request response payload
      https.end();
}

