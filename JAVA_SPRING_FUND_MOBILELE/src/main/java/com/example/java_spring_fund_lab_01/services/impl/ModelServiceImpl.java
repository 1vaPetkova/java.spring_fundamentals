package com.example.java_spring_fund_lab_01.services.impl;

import com.example.java_spring_fund_lab_01.models.entities.Brand;
import com.example.java_spring_fund_lab_01.models.entities.Model;
import com.example.java_spring_fund_lab_01.models.entities.enums.CategoryEnum;
import com.example.java_spring_fund_lab_01.repositories.BrandRepository;
import com.example.java_spring_fund_lab_01.repositories.ModelRepository;
import com.example.java_spring_fund_lab_01.services.ModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelServiceImpl implements ModelService {
    private final ModelRepository modelRepository;
    private final BrandRepository brandRepository;

    public ModelServiceImpl(ModelRepository modelRepository, BrandRepository brandRepository) {
        this.modelRepository = modelRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public void initializeModels() {
        if (this.modelRepository.count() == 0) {
            Brand ford = this.brandRepository.findByName("Ford")
                    .orElseThrow(IllegalArgumentException::new);

            Model fiesta = new Model();
            fiesta
                    .setName("Fiesta")
                    .setCategory(CategoryEnum.CAR)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/7/7d/2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg/1920px-2017_Ford_Fiesta_Zetec_Turbo_1.0_Front.jpg")
                    .setStartYear(1976)
                    .setBrand(ford);

            Model escort = new Model();
            escort
                    .setName("Escort")
                    .setCategory(CategoryEnum.CAR)
                    .setImageUrl("https://upload.wikimedia.org/wikipedia/commons/thumb/3/39/Ford_Escort_RS2000_MkI.jpg/420px-Ford_Escort_RS2000_MkI.jpg")
                    .setStartYear(1967)
                    .setEndYear(2004)
                    .setBrand(ford);

            modelRepository.saveAll(List.of(fiesta, escort));
        }
    }
}

