package com.example.weatherstation.Controller;

import com.example.weatherstation.DaoDB.TempDao;
import com.example.weatherstation.Model.Temp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

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


    @RequestMapping("/")
    public String get_all_data(Model model)
    {
        List<Temp> temp_data = new ArrayList<>();
        temp_data = dao.get_all_data();
        model.addAttribute("temp", temp_data);

        return "calle";
    }
}
