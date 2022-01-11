package com.example.java_spring_fund_pathfinder.web;

import com.example.java_spring_fund_pathfinder.models.view.RouteViewModel;
import com.example.java_spring_fund_pathfinder.services.RouteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/routes")
public class RouteController {

    private final RouteService routeService;

    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }

    @GetMapping("/all")
    public String allRoutes(Model model){
        List<RouteViewModel> routeViewModels = this.routeService.findAllRoutesView();
        return "routes";
    }
}
