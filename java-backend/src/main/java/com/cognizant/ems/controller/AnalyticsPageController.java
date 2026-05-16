package com.cognizant.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AnalyticsPageController {

    @GetMapping("/analytics")
    public String viewAnalytics() {
        return "analytics";
    }
}
