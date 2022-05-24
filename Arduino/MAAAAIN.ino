#include <SoftwareSerial.h>
#include <stdlib.h>
#define ssid "Wu Tang LAN"    // "SSID-WiFiname" 
#define password "hexkjlmpa"       // "password"
#define IP "192.168.0.132" 

/*
#define ssid "Tacobaco"    // "SSID-WiFiname" 
#define password "nejnejnej"       // "password"
#define IP "172.20.10.8" // mobil
*/




SoftwareSerial ESPserial(2, 3); // RX | TX

const int sensorPin = A0;
bool con = false;
String temp_msg;
float tempTemp;
float temperature;

void setup() {
  
  Serial.begin(9600);

  ESPserial.begin(9600);
    
  Serial.println("Arduino Starting..");
  
}

void loop() {

  Serial.println("Trying to connect to server");
  connectTCP();
  
  while(con) {

    getTemp();
    if (temperature != tempTemp) {
      tempTemp = temperature;
      ESPserial.println("AT+CIPSEND=8");
      delay(1000);
      if(ESPserial.find("ERROR")) {
        Serial.println("Got error with preparing tcp packet");
        con = false;
        break;
      } else {
        setTemp();
        ESPserial.println(temp_msg); //\r\n
        Serial.println("Temperature sent!");
      }
      delay(500);
    }
  }
}

void getTemp() {
  int sensorVal = analogRead(sensorPin);
  float voltage = (sensorVal*5.0) / 1024.0; //maybe 1023.0?
  temperature = (voltage - 0.5) * 100;
 
}

void setTemp() {
  temp_msg = (String)temperature;
  temp_msg += "\n";
}

void connectTCP() {
  String cmd = "AT+CIPSTART=\"TCP\",\"";
  cmd += IP;
  cmd += "\",1312";
  ESPserial.println(cmd);
  delay(2000);

  if(ESPserial.find("ERROR")){
    Serial.println("ERROR - CONNECTION SERVER");
    con = false;
  } else if(ESPserial.find("OK")) {
    Serial.println("OK - CONNECTION SERVER");
    con = true;
  } else if(ESPserial.find("CONNECT")) {
    Serial.println("CONNECT - CONNECTION SERVER");
    con = true;
  } else {
    Serial.println("ELSE - CONNECTION SERVER");
    con = true;
  }
  
}
