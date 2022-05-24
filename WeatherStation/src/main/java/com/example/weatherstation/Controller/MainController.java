
package com.example.weatherstation.Controller;

import com.example.weatherstation.DaoDB.TempDao;
import com.example.weatherstation.Model.Temp;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;

@Controller
public class MainController
{
    TempDao dao = new TempDao();

    @RequestMapping("/getgraph")
    public String get_graph(Model model) {
        List<Temp> array = dao.get_all_data();
        model.addAttribute("array", array);
        model.addAttribute("temp", array);
        return "graph";
    }

}

