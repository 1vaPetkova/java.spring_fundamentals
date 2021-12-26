package com.example.java_spring_fund_lab_01.web;

import com.example.java_spring_fund_lab_01.services.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/offers")
public class OffersController {
    private final OfferService offerService;

    public OffersController(OfferService offerService) {
        this.offerService = offerService;
    }
    @GetMapping("/all")
    public String allBrands(Model model) {
        model.addAttribute("offers", offerService.getAllOffers());
        return "offers";
    }
}