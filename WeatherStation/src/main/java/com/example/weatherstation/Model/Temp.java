package com.example.weatherstation.Model;

import lombok.Data;

@Data
public class Temp

{
    int id;
    Double temperature;
    String created;
    int areaId;

    public Temp(){}
}
