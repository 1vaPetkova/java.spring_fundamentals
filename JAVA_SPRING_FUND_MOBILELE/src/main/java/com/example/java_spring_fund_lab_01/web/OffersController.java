package com.example.java_spring_fund_lab_01.web;


import javax.validation.Valid;

import com.example.java_spring_fund_lab_01.model.binding.OfferAddBindModel;
import com.example.java_spring_fund_lab_01.model.binding.OfferUpdateBindingModel;
import com.example.java_spring_fund_lab_01.model.entity.enums.EngineEnum;
import com.example.java_spring_fund_lab_01.model.entity.enums.TransmissionEnum;
import com.example.java_spring_fund_lab_01.model.service.OfferAddServiceModel;
import com.example.java_spring_fund_lab_01.model.service.OfferUpdateServiceModel;
import com.example.java_spring_fund_lab_01.model.view.OfferDetailsView;
import com.example.java_spring_fund_lab_01.service.BrandService;
import com.example.java_spring_fund_lab_01.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
public class OffersController {
    private final OfferService offerService;
    private final ModelMapper modelMapper;
    private final BrandService brandService;

    public OffersController(OfferService offerService,
                            ModelMapper modelMapper, BrandService brandService) {
        this.offerService = offerService;
        this.modelMapper = modelMapper;
        this.brandService = brandService;
    }

    // GET
    @GetMapping("/offers/all")
    public String allOffers(Model model) {
        model.addAttribute("offers",
                offerService.getAllOffers());
        return "offers";
    }

    @GetMapping("/offers/{id}/details")
    public String showOffer(
            @PathVariable Long id, Model model) {
        model.addAttribute("offer", this.offerService.findById(id));
        return "details";
    }

    // DELETE
    @DeleteMapping("/offers/{id}")
    public String deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);

        return "redirect:/offers/all";
    }

    // UPDATE

    @GetMapping("/offers/{id}/edit")
    public String editOffer(@PathVariable Long id, Model model) {

        OfferDetailsView offerDetailsView = offerService.findById(id);
        OfferUpdateBindingModel offerModel = modelMapper.map(
                offerDetailsView,
                OfferUpdateBindingModel.class
        );
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        model.addAttribute("offerModel", offerModel);
        return "update";
    }

    @GetMapping("/offers/{id}/edit/errors")
    public String editOfferErrors(@PathVariable Long id, Model model) {
        model.addAttribute("engines", EngineEnum.values());
        model.addAttribute("transmissions", TransmissionEnum.values());
        return "update";
    }

    @PatchMapping("/offers/{id}/edit")
    public String editOffer(
            @PathVariable Long id,
            @Valid OfferUpdateBindingModel offerModel,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {

            redirectAttributes.addFlashAttribute("offerModel", offerModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.offerModel", bindingResult);

            return "redirect:/offers/" + id + "/edit/errors";
        }

        OfferUpdateServiceModel serviceModel = modelMapper.map(offerModel,
                OfferUpdateServiceModel.class);
        serviceModel.setId(id);

        offerService.updateOffer(serviceModel);

        return "redirect:/offers/" + id + "/details";
    }

    @GetMapping("/offers/add")
    public String getAddOfferPage(Model model) {

        if (!model.containsAttribute("offerAddBindModel")) {
            model.
                    addAttribute("offerAddBindModel", new OfferAddBindModel()).
                    addAttribute("brandsModels", brandService.getAllBrands());
        }
        return "offer-add";
    }

    @PostMapping("/offers/add")
    public String addOffer(@Valid OfferAddBindModel offerAddBindModel,
                           BindingResult bindingResult, RedirectAttributes redirectAttributes,
                           Principal principal) {
        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("offerAddBindModel", offerAddBindModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.offerAddBindModel", bindingResult)
                    .addFlashAttribute("brandsModels", brandService.getAllBrands());
            return "redirect:/offers/add";
        }
        OfferAddServiceModel savedOfferAddServiceModel = offerService.addOffer(offerAddBindModel, principal);
        return "redirect:/offers/" + savedOfferAddServiceModel.getId() + "/details";
    }
}
