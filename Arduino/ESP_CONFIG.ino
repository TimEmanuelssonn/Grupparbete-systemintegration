#include <SoftwareSerial.h>
#include <stdlib.h>
#define ssid "Wu Tang LAN"    // "SSID-WiFiname" 
#define password "hexkjlmpa"       // "password"
#define IP "192.168.0.132" 

SoftwareSerial ESPserial(2, 3); // RX | TX

void setup() {
  Serial.begin(9600);
  ESPserial.begin(9600);

  Serial.println("Starting arudino");

  ESPserial.println("AT");
  delay(2000);
  if ( ESPserial.find("OK")) {
    Serial.println("AT - OK FROM ESP");
   } else {
    Serial.println("AT - Error");
   }
   connectWiFi();
}

void loop() {
  Serial.println("Set up done!");
  delay(3000);

}

void connectWiFi(){
  
  ESPserial.println("AT+CWMODE=1");
  delay(2000);
  if ( ESPserial.find("OK")) {
      Serial.println("AT+CWMODE=1 - OK");
     } else {
      Serial.println("AT+CWMODE=1 - ERROR");
     }
  
  String cmd="AT+CWJAP=\"";
  cmd+=ssid;
  cmd+="\",\"";
  cmd+=password;
  cmd+="\"";
  
  ESPserial.println(cmd);
  delay(5000);
  if ( ESPserial.find("OK")) {
      Serial.println("Connected to Wi-Fi");
  } else if (ESPserial.find("ERROR")) {
    Serial.println("Error with connecting to Wi-Fi");
  } else if (ESPserial.find("CONNECTED")) {
    Serial.println("Already connected");
  } else {
    Serial.println("Something else with connection..");
  }
}
