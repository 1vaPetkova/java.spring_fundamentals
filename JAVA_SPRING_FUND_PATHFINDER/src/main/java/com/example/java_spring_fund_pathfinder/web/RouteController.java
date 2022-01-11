package com.example.java_spring_fund_pathfinder.web;

import com.example.java_spring_fund_pathfinder.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model) {
        model.addAttribute("routes", this.routeService.findAllRoutesView());
        return "routes";
    }

    @GetMapping("/add")
    public String addRoute() {
        return "add-route";
    }

}
