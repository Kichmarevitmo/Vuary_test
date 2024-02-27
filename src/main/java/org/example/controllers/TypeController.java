package org.example.controllers;

import org.example.entities.Type;
import org.example.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/types")
public class TypeController {

    @Autowired private TypeService typeService;

    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAll(){

        Map<String, Object> response = new HashMap<>();

        List<Type> types = typeService.getAll();

        return ResponseEntity.ok(response);
    }
}
