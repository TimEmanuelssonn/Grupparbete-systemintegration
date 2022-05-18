package com.example.weatherstation.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController
{
    @RequestMapping("/index")
    public String returnIndex()
    {
        return "index";
        //Länk till hemsidan för fullständig rapport
        //Live-rapport
    }

    @RequestMapping("/report")
    public String returnReport()
    {
        return "report";
        //Full historik
    }
}
