package com.arjun.coronavirustracker.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.arjun.coronavirustracker.model.Places;
import com.arjun.coronavirustracker.services.CoronaVirusService;

import java.util.List;

@Controller
public class MainController {
    @Autowired
    CoronaVirusService coronaVirusService;

    @GetMapping("/")
    public String home(Model model) {
        List<Places> allStats = coronaVirusService.getAllStats();
        int totalReportedCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
        int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffFromPrevDay()).sum();
        model.addAttribute("locationStats", allStats);
        model.addAttribute("totalReportedCases", totalReportedCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
