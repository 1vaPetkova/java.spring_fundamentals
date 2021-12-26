package com.example.java_spring_fund_lab_01;

import com.example.java_spring_fund_lab_01.models.entities.BaseEntity;
import com.example.java_spring_fund_lab_01.models.entities.Brand;
import com.example.java_spring_fund_lab_01.models.entities.Model;
import com.example.java_spring_fund_lab_01.models.entities.Offer;
import com.example.java_spring_fund_lab_01.models.entities.enums.Category;
import com.example.java_spring_fund_lab_01.models.entities.enums.Engine;
import com.example.java_spring_fund_lab_01.models.entities.enums.Transmission;
import com.example.java_spring_fund_lab_01.repositories.BrandRepository;
import com.example.java_spring_fund_lab_01.repositories.ModelRepository;
import com.example.java_spring_fund_lab_01.repositories.OfferRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Component
public class DBInit implements CommandLineRunner {
    private final BrandRepository brandRepository;
    private final ModelRepository modelRepository;
    private final OfferRepository offerRepository;

    public DBInit(BrandRepository brandRepository, ModelRepository modelRepository, OfferRepository offerRepository) {
        this.brandRepository = brandRepository;
        this.modelRepository = modelRepository;
        this.offerRepository = offerRepository;
    }

    @Transactional
    @Override
    public void run(String... args) {
        Brand fordBrand = new Brand();
        fordBrand.setName("Ford");
        setCurrentTimeStamps(fordBrand);

        Brand hondaBrand = new Brand();
        hondaBrand.setName("Honda");
        setCurrentTimeStamps(hondaBrand);
        //Create brands
        if (this.brandRepository.count() == 0) {
            this.brandRepository.saveAllAndFlush(List.of(fordBrand, hondaBrand));
        }
        //Create models
        Model fiesta = initFiesta(fordBrand);
        Model escort = initEscort(fordBrand);
        Model nc750S = initNC750S(hondaBrand);
        if (this.modelRepository.count() == 0) {
            this.modelRepository.saveAllAndFlush(List.of(fiesta, escort, nc750S));
        }

        //Create offer
        Offer fiestaOffer = createFiestaOffer(fiesta);
        if (this.offerRepository.count() == 0) {
            this.offerRepository.saveAndFlush(fiestaOffer);
        }
    }

    private Offer createFiestaOffer(Model fiestaModel) {
        Offer offer = new Offer();
        offer
                .setEngine(Engine.GASOLINE)
                .setImageUrl("https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fwww.investireoggi.it%2Fmotori%2Fwp-content%2Fuploads%2Fsites%2F17%2F2020%2F02%2FFord-Fiesta.jpg&f=1&nofb=1")
                .setMileage(80000)
                .setPrice(new BigDecimal(10000))
                .setYear(2005)
                .setDescription("Driven by a german grandmother")
                .setTransmission(Transmission.MANUAL)
                .setModel(fiestaModel);
        setCurrentTimeStamps(offer);
        return offer;
    }


    private Model initNC750S(Brand hondaBrand) {
        Model nc750s = new Model();
        nc750s.setName("NC750S")
                .setCategory(Category.MOTORCYCLE)
                .setImageUrl("https://image.hojtorget.se/live/51633/honda-nc750s-91379759-medium.jpg")
                .setBrand(hondaBrand)
                .setStartYear(2014);
        setCurrentTimeStamps(nc750s);
        return nc750s;
    }

    private Model initEscort(Brand fordBrand) {
        Model escort = new Model();
        escort.setName("Escort")
                .setCategory(Category.CAR)
                .setImageUrl("https://www.automobilemag.com/uploads/sites/11/2017/04/1995-Ford-Escort-RS-Cosworth-Just-Listed-Front-Three-Quarters.jpg")
                .setBrand(fordBrand)
                .setStartYear(1985);
        setCurrentTimeStamps(escort);
        return escort;
    }

    private Model initFiesta(Brand brand) {
        Model fiesta = new Model();
        fiesta.setName("Fiesta")
                .setCategory(Category.CAR)
                .setImageUrl("https://mediacloud.carbuyer.co.uk/image/private/s--5U648ps7--/f_auto,t_content-image-full-desktop@1/v1579643421/carbuyer/2019/04/fiesta-st-ford-performance-edition-limited-to-600-units.jpg")
                .setBrand(brand)
                .setStartYear(1968)
                .setEndYear(2002);
        setCurrentTimeStamps(fiesta);
        return fiesta;
    }

    private static void setCurrentTimeStamps(BaseEntity baseEntity) {
        baseEntity.setCreated(Instant.now());
        baseEntity.setModified(Instant.now());
    }

}