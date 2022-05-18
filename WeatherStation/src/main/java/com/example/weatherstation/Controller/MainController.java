package com.example.weatherstation.Controller;

import com.example.weatherstation.DaoDB.TempDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class MainController
{
    ArrayList<Integer> temps = new ArrayList<Integer>();


    TempDao dao = new TempDao();

    @RequestMapping("/index")
    public String returnIndex(Model model)
    {
        model.addAttribute("temp", 25);
        return "index";
        //Länk till hemsidan för fullständig rapport
        //Live-rapport
    }

    @RequestMapping("/report")
    public String returnReport(Model model)
    {
        temps.add(21);
        temps.add(33);
        temps.add(24);
        temps.add(35);
        model.addAttribute("temps", temps);
        return "fullreport";
        //Full historik
    }
}
