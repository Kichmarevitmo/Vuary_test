package org.example.domain.equipment.jsonhandler;

import org.example.domain.User;
import org.example.domain.equipment.ainova.AINOVA;
import org.example.domain.equipment.ainova.AINOVARepo;
import org.example.domain.equipment.salmi.SALMI;
import org.example.domain.equipment.salmi.SALMIRepo;
import org.example.domain.equipment.suari.SUARI;
import org.example.domain.equipment.suari.SUARIRepo;
import org.example.domain.equipment.toivo.TOIVO;
import org.example.domain.equipment.toivo.TOIVORepo;
import org.example.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")

public class ProductController {
    @Autowired
    private TOIVORepo toivoRepo;
    @Autowired
    private SUARIRepo suariRepo;
    @Autowired
    private SALMIRepo salmiRepo;
    @Autowired
    private AINOVARepo ainovaRepo;

    @GetMapping("/allAinova")
    public List<AINOVA> getAllProductsAINOVA() {
        return ainovaRepo.findAll();
    }
    @GetMapping("/allSalmi")
    public List<SALMI> getAllProductsSALMI() {
        return salmiRepo.findAll();
    }
    @GetMapping("/allSuari")
    public List<SUARI> getAllProductsSUARI() {
        return suariRepo.findAll();
    }
    @GetMapping("/allToivo")
    public List<TOIVO> getAllProductsTOIVO() {
        return toivoRepo.findAll();
    }

}
